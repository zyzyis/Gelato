package qa.qcri.nadeef.test.rulebuilder;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import qa.qcri.nadeef.core.util.Bootstrap;
import qa.qcri.nadeef.ruleext.DCRuleBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class DCRuleBuilderTest {

    private static File workingDirectory;

    @Before
    public void setup() {
        try {
            Bootstrap.start();
            workingDirectory = Files.createTempDir();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @After
    public void tearDown() {
        Bootstrap.shutdown();
        workingDirectory.delete();
    }

    @Test
    public void testParser() throws Exception{
        List<String> values = Lists.newArrayList();
        values.add("not(t1.C=t2.C&t1.A!=t2.A)");
        values.add("not(t1.C>t1.B)");
        values.add("not(t1.D=t2.D)");
        values.add("not(t1.A='TEST')");
        values.add("not(t1.B<=1)");
        try {
            for (int i = 0; i < values.size(); i ++){
                DCRuleBuilder dcRuleBuilder = new DCRuleBuilder();
                String str = values.get(i);

                File output =
                    dcRuleBuilder.name("dc"+i)
                        .table("table")
                        .value(str)
                        .out(workingDirectory)
                        .compile()
                        .iterator()
                        .next();
                System.out.println("Write file in " + output.getAbsolutePath());
            }
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testInvalidParser() throws Exception {
        List<String> values = new ArrayList<>();
        values.add("not(C=C");
        values.add("not(t1.C)");
        values.add("not(t1.C!t2.C");
        values.add("()");
        try {
            for (int i = 0; i < values.size(); i ++) {
                DCRuleBuilder dcRuleBuilder = new DCRuleBuilder();
                String str = values.get(i);
                File output =
                    dcRuleBuilder
                        .name("dc"+i)
                        .table("table")
                        .value(str)
                        .out(workingDirectory)
                        .compile()
                        .iterator()
                        .next();
                System.out.println("Write file in " + output.getAbsolutePath());
            }
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }

    }
}