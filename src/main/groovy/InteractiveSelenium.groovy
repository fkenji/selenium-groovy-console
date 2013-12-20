import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class InteractiveSelenium {

    private GroovyShell groovyShell;
    private Binding binding;
    private WebDriver driver;

    private String[] commands;

    public InteractiveSelenium() {
        this(new ChromeDriver());
    }

    public InteractiveSelenium(WebDriver driver) {
        Binding binding = new Binding();
        binding.setVariable("selenium", driver);
        binding.setVariable("By", org.openqa.selenium.By.class)

        this.groovyShell = new GroovyShell(binding);
    }


    def evaluate(String command) {

        this.groovyShell.evaluate(command);
    }

    def runWith(InputStream input, PrintStream err) {
        println "GroovySelenium Interactive Console:"

        Reader inputStreamReader = new BufferedReader(new InputStreamReader(input))
        print ">>"

        while (true) {
            String line = inputStreamReader.readLine();
            println ">> ${line}";

            try {
                println this.evaluate(line);
            } catch (Exception e) {
                e.printStackTrace(err);
            };
            print ">>"
        }

    }


    public static void main(String[] args) {
        InteractiveSelenium interactiveSelenium = new InteractiveSelenium();
        interactiveSelenium.runWith(System.in, System.err)
    }


}
