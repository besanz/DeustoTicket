import os
import javalang
from pathlib import Path


IGNORED_DIRECTORIES = {".git", "bin", ".vscode", "lib", "security"}


def process_java_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        java_code = file.read()
        try:
            tree = javalang.parse.parse(java_code)
            class_info = []
            for path, node in tree.filter(javalang.tree.ClassDeclaration):
                class_name = node.name
                attributes = ', '.join([f"{declarator.name}-{field.type.name}" for field in node.fields for declarator in field.declarators])
                methods = '; '.join([f"{method.name}({', '.join([f'{param.name}-{param.type.name}' for param in method.parameters])})" for method in node.methods])
                if attributes:
                    class_info.append(f"{class_name} ({attributes})")
                else:
                    class_info.append(f"{class_name}")
                if methods:
                    class_info.append(f"[{methods}]")
            return ' '.join(class_info)
        except javalang.parser.JavaSyntaxError as e:
            return f"Error de sintaxis en archivo {file_path}: {e}"


def process_directory(directory, depth=0):
    tree_structure = []
    for entry in os.scandir(directory):
        if entry.name in IGNORED_DIRECTORIES:
            continue
        if entry.is_file():
            file_info = f"{'  ' * depth}{entry.name}"
            if entry.name.endswith(".java"):
                file_info += " "
                file_info += process_java_file(entry.path)
            tree_structure.append(file_info)
        elif entry.is_dir():
            tree_structure.append(f"{'  ' * depth}{entry.name}")
            tree_structure.extend(process_directory(entry.path, depth + 1))
    return tree_structure


def main():
    project_path = input("Introduce la ruta del proyecto Java: ")
    output_file = "project_tree.txt"

    tree_structure = process_directory(project_path)

    with open(output_file, "w", encoding="utf-8") as f:
        f.write('\n'.join(tree_structure))

    print(f"El archivo {output_file} se ha generado con éxito.")


if __name__ == "__main__":
    main()
