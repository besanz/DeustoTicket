import os
import re

def analizar_archivos(directorio, palabras_clave):
    for raiz, _, archivos in os.walk(directorio):
        for archivo in archivos:
            if archivo.endswith(('.java', '.xml')):
                archivo_path = os.path.join(raiz, archivo)
                with open(archivo_path, 'r', encoding='utf-8') as f:
                    contenido = f.read()
                    
                for palabra in palabras_clave:
                    if re.search(palabra, contenido, re.IGNORECASE):
                        print(f'Palabra clave "{palabra}" encontrada en {archivo_path}')

def main():
    directorio_proyecto = input("Por favor, ingresa el directorio del proyecto: ")
    palabras_clave = [
        'UserDAO',
        'UserService',
        'UserController',
        'LoginUser',
        'data.entidades.User',
        'NucleusUserException',
        'StaffDAO',
        'StaffService',
        'StaffController',
        'LoginStaff',
        'data.entidades.Staff',
    ]
    analizar_archivos(directorio_proyecto, palabras_clave)

if __name__ == "__main__":
    main()
