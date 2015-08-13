package parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
//import java.util.stream.Collectors;






import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.beust.jcommander.JCommander;

import parser.cmdline.Arguments;
import parser.cmdline.OptionsDescriptions;

public class Main implements Runnable {
//    private final Parser parser;

    public static void main(String[] args) throws IOException {
//        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"CommandLineParserContext.xml"});
        ApplicationContext context = null;
        Resource resource = new ClassPathResource("/my.properties");
        ResourceBundle descriptions = new OptionsDescriptions(PropertiesLoaderUtils.loadProperties(resource));
        
        Main program = new Main(args, context);
        program.run();
    }

    public Main(String[] args, ApplicationContext applicationContext) throws FileNotFoundException {
//        ResourceBundleMessageSource resourceBundle = (ResourceBundleMessageSource) applicationContext.getBean("messages");
        Arguments arguments = new Arguments();
        JCommander cmd = new JCommander(arguments, ResourceBundle.getBundle("lineParserDescriptions"));
        cmd.parse(args);
        System.out.println(arguments);
//        this.parser = new Parser();
    }

    @Override
    public void run() {
//        arguments.getSources().stream().parallel().map(parser::parse).collect(Collectors.toList());
    }
}
