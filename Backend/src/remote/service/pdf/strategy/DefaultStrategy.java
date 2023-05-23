package remote.service.pdf.strategy;

public class DefaultStrategy implements TemplateStrategy {
    @Override
    public String getTemplateName() {
        return "templates/default.pdf";
    }
}
