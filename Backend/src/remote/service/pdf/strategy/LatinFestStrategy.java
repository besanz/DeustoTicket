package remote.service.pdf.strategy;

public class LatinFestStrategy implements TemplateStrategy {
    @Override
    public String getTemplateName() {
        return "templates/template2.pdf";
    }
}
