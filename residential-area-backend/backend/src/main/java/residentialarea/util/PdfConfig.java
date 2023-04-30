package residentialarea.util;

import freemarker.template.Configuration;
import org.springframework.stereotype.Component;

@Component
public class PdfConfig {

    public Configuration getFreeMarkerConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        return cfg;
    }
}
