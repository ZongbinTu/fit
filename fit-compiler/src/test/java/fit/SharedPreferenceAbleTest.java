package fit;

import com.google.testing.compile.JavaFileObjects;
import fit.compiler.FitProcessor;
import javax.tools.JavaFileObject;
import org.junit.Test;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static java.util.Arrays.asList;

/**
 * @auther Tu enum@foxmail.com
 * @date 9/5/16
 */
public class SharedPreferenceAbleTest {
  @Test public void sharedPreferenceAble() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
        + "package test;\n"
        + "import fit.SharedPreferenceAble;\n"
        + "@SharedPreferenceAble public class Test {\n"
        + "    public String aT;\n"
        + "}");

    JavaFileObject sharedSource = JavaFileObjects.forSourceString("test/Test_Preference", ""
        + "package test;\n"
        + "import android.content.Context;\n"
        + "import android.content.SharedPreferences;\n"
        + "import fit.MM;\n"
        + "import fit.internal.Utils;\n"
        + "import java.lang.Override;\n"
        + "public class Test_Preference implements MM<Test> {\n"
        + "  @Override public void save(Context context, Test obj) {\n"
        + "    SharedPreferences.Editor editor = Utils.getSharedPreferenceEditor(context, \"test.Test\");\n"
        + "    editor.putString(\"aT\", obj.aT);\n"
        + "    editor.apply();\n"
        + "  }\n"
        + "  @Override public Test get(Context context) {\n"
        + "    SharedPreferences sharedPreferences = context.getSharedPreferences(\"test.Test\", Context.MODE_PRIVATE);\n"
        + "    Test obj = new Test();\n"
        + "    obj.aT = sharedPreferences.getString(\"aT\", null);\n"
        + "    return obj;\n"
        + "  }\n"
        + "  @Override public void clear(Context context) {\n"
        + "    Utils.getSharedPreferenceEditor(context, \"test.Test\").clear().apply();\n"
        + "  }\n"
        + "}");

    assertAbout(javaSource()).that(source)
        .withCompilerOptions("-Xlint:-processing")
        .processedWith(new FitProcessor())
        .compilesWithoutWarnings()
        .and()
        .generatesSources(sharedSource);
  }

  @Test public void sharedPreferenceAbleViewFinalClass() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
        + "package test;\n"
        + "import fit.SharedPreferenceAble;\n"
        + "@SharedPreferenceAble public final class Test {\n"
        + "    public String aT;\n"
        + "}");

    JavaFileObject sharedSource = JavaFileObjects.forSourceString("test/Test_Preference", ""
        + "package test;\n"
        + "import android.content.Context;\n"
        + "import android.content.SharedPreferences;\n"
        + "import fit.MM;\n"
        + "import fit.internal.Utils;\n"
        + "import java.lang.Override;\n"
        + "public final class Test_Preference implements MM<Test> {\n"
        + "  @Override public void save(Context context, Test obj) {\n"
        + "    SharedPreferences.Editor editor = Utils.getSharedPreferenceEditor(context, \"test.Test\");\n"
        + "    editor.putString(\"aT\", obj.aT);\n"
        + "    editor.apply();\n"
        + "  }\n"
        + "  @Override public Test get(Context context) {\n"
        + "    SharedPreferences sharedPreferences = context.getSharedPreferences(\"test.Test\", Context.MODE_PRIVATE);\n"
        + "    Test obj = new Test();\n"
        + "    obj.aT = sharedPreferences.getString(\"aT\", null);\n"
        + "    return obj;\n"
        + "  }\n"
        + "  @Override public void clear(Context context) {\n"
        + "    Utils.getSharedPreferenceEditor(context, \"test.Test\").clear().apply();\n"
        + "  }\n"
        + "}");

    assertAbout(javaSource()).that(source)
        .withCompilerOptions("-Xlint:-processing")
        .processedWith(new FitProcessor())
        .compilesWithoutWarnings()
        .and()
        .generatesSources(sharedSource);
  }

  @Test public void sharedPreferenceUppercasePackageName() {
    JavaFileObject source = JavaFileObjects.forSourceString("com.Example.Test", ""
        + "package test.Example;\n"
        + "import fit.SharedPreferenceAble;\n"
        + "@SharedPreferenceAble public class Test {\n"
        + "    public String aT;\n"
        + "}");

    JavaFileObject sharedSource = JavaFileObjects.forSourceString("test/Test_Preference", ""
        + "package test.Example;\n"
        + "import android.content.Context;\n"
        + "import android.content.SharedPreferences;\n"
        + "import fit.MM;\n"
        + "import fit.internal.Utils;\n"
        + "import java.lang.Override;\n"
        + "public class Test_Preference implements MM<Test> {\n"
        + "  @Override public void save(Context context, Test obj) {\n"
        + "    SharedPreferences.Editor editor = Utils.getSharedPreferenceEditor(context, \"test.Example.Test\");\n"
        + "    editor.putString(\"aT\", obj.aT);\n"
        + "    editor.apply();\n"
        + "  }\n"
        + "  @Override public Test get(Context context) {\n"
        + "    SharedPreferences sharedPreferences = context.getSharedPreferences(\"test.Example.Test\", Context.MODE_PRIVATE);\n"
        + "    Test obj = new Test();\n"
        + "    obj.aT = sharedPreferences.getString(\"aT\", null);\n"
        + "    return obj;\n"
        + "  }\n"
        + "  @Override public void clear(Context context) {\n"
        + "    Utils.getSharedPreferenceEditor(context, \"test.Example.Test\").clear().apply();\n"
        + "  }\n"
        + "}");

    assertAbout(javaSource()).that(source)
        .withCompilerOptions("-Xlint:-processing")
        .processedWith(new FitProcessor())
        .compilesWithoutWarnings()
        .and()
        .generatesSources(sharedSource);
  }

  @Test public void sharedPreferenceObject() throws Exception {
    JavaFileObject source = JavaFileObjects.forSourceString("com.Example.Test", ""
        + "package test.Example;\n"
        + "import fit.SharedPreferenceAble;\n"
        + "import java.util.Date;\n"
        + "@SharedPreferenceAble public class Test {\n"
        + "    public Date birthday;\n"
        + "    public String aT;\n"
        + "}");

    JavaFileObject sharedSource = JavaFileObjects.forSourceString("test/Test_Preference", ""
        + "package test.Example;\n"
        + "import android.content.Context;\n"
        + "import android.content.SharedPreferences;\n"
        + "import fit.MM;\n"
        + "import fit.internal.Utils;\n"
        + "import java.lang.Override;\n"
        + "public class Test_Preference implements MM<Test> {\n"
        + "  @Override public void save(Context context, Test obj) {\n"
        + "    SharedPreferences.Editor editor = Utils.getSharedPreferenceEditor(context, \"test.Example.Test\");\n"
        + "    editor.putString(\"aT\", obj.aT);\n"
        + "    editor.apply();\n"
        + "  }\n"
        + "  @Override public Test get(Context context) {\n"
        + "    SharedPreferences sharedPreferences = context.getSharedPreferences(\"test.Example.Test\", Context.MODE_PRIVATE);\n"
        + "    Test obj = new Test();\n"
        + "    obj.aT = sharedPreferences.getString(\"aT\", null);\n"
        + "    return obj;\n"
        + "  }\n"
        + "  @Override public void clear(Context context) {\n"
        + "    Utils.getSharedPreferenceEditor(context, \"test.Example.Test\").clear().apply();\n"
        + "  }\n"
        + "}");

    assertAbout(javaSource()).that(source)
        .withCompilerOptions("-Xlint:-processing")
        .processedWith(new FitProcessor())
        .compilesWithoutWarnings()
        .and()
        .generatesSources(sharedSource);
  }

  @Test public void superclass() {
    JavaFileObject source1 = JavaFileObjects.forSourceString("test.Test", ""
        + "package test;\n"
        + "import fit.SharedPreferenceAble;\n"
        + "@SharedPreferenceAble public class Test {\n"
        + "    public String aT;\n"
        + "}");

      JavaFileObject source2 = JavaFileObjects.forSourceString("test.TestOne", ""
          + "package test;\n"
          + "import fit.SharedPreferenceAble;\n"
          + "@SharedPreferenceAble public class TestOne extends Test {\n"
          + "  public int thing;\n"
          + "}"
      );

      JavaFileObject source3 = JavaFileObjects.forSourceString("test.TestTwo", ""
          + "package test;\n"
          + "import fit.SharedPreferenceAble;\n"
          + "@SharedPreferenceAble public class TestTwo extends Test {\n"
          + "}"
      );


    JavaFileObject sharedSource1 = JavaFileObjects.forSourceString("test/Test_Preference", ""
        + "package test;\n"
        + "import android.content.Context;\n"
        + "import android.content.SharedPreferences;\n"
        + "import fit.MM;\n"
        + "import fit.internal.Utils;\n"
        + "import java.lang.Override;\n"
        + "public class Test_Preference implements MM<Test> {\n"
        + "  @Override public void save(Context context, Test obj) {\n"
        + "    SharedPreferences.Editor editor = Utils.getSharedPreferenceEditor(context, \"test.Test\");\n"
        + "    editor.putString(\"aT\", obj.aT);\n"
        + "    editor.apply();\n"
        + "  }\n"
        + "  @Override public Test get(Context context) {\n"
        + "    SharedPreferences sharedPreferences = context.getSharedPreferences(\"test.Test\", Context.MODE_PRIVATE);\n"
        + "    Test obj = new Test();\n"
        + "    obj.aT = sharedPreferences.getString(\"aT\", null);\n"
        + "    return obj;\n"
        + "  }\n"
        + "  @Override public void clear(Context context) {\n"
        + "    Utils.getSharedPreferenceEditor(context, \"test.Test\").clear().apply();\n"
        + "  }\n"
        + "}");

    JavaFileObject sharedSource2 = JavaFileObjects.forSourceString("test/TestOne_Preference", ""
        + "package test;\n"
        + "import android.content.Context;\n"
        + "import android.content.SharedPreferences;\n"
        + "import fit.MM;\n"
        + "import fit.internal.Utils;\n"
        + "import java.lang.Override;\n"
        + "public class TestOne_Preference implements MM<TestOne> {\n"
        + "  @Override public void save(Context context, TestOne obj) {\n"
        + "    SharedPreferences.Editor editor = Utils.getSharedPreferenceEditor(context, \"test.TestOne\");\n"
        + "    editor.putString(\"aT\", obj.aT);\n"
        + "    editor.putInt(\"thing\", obj.thing);\n"
        + "    editor.apply();\n"
        + "  }\n"
        + "  @Override public TestOne get(Context context) {\n"
        + "    SharedPreferences sharedPreferences = context.getSharedPreferences(\"test.TestOne\", Context.MODE_PRIVATE);\n"
        + "    TestOne obj = new TestOne();\n"
        + "    obj.aT = sharedPreferences.getString(\"aT\", null);\n"
        + "    obj.thing = sharedPreferences.getInt(\"thing\", 0);\n"
        + "    return obj;\n"
        + "  }\n"
        + "  @Override public void clear(Context context) {\n"
        + "    Utils.getSharedPreferenceEditor(context, \"test.TestOne\").clear().apply();\n"
        + "  }\n"
        + "}");

    JavaFileObject sharedSource3 = JavaFileObjects.forSourceString("test/TestTwo_Preference", ""
        + "package test;\n"
        + "import android.content.Context;\n"
        + "import android.content.SharedPreferences;\n"
        + "import fit.MM;\n"
        + "import fit.internal.Utils;\n"
        + "import java.lang.Override;\n"
        + "public class TestTwo_Preference implements MM<TestTwo> {\n"
        + "  @Override public void save(Context context, TestTwo obj) {\n"
        + "    SharedPreferences.Editor editor = Utils.getSharedPreferenceEditor(context, \"test.TestTwo\");\n"
        + "    editor.putString(\"aT\", obj.aT);\n"
        + "    editor.apply();\n"
        + "  }\n"
        + "  @Override public TestTwo get(Context context) {\n"
        + "    SharedPreferences sharedPreferences = context.getSharedPreferences(\"test.TestTwo\", Context.MODE_PRIVATE);\n"
        + "    TestTwo obj = new TestTwo();\n"
        + "    obj.aT = sharedPreferences.getString(\"aT\", null);\n"
        + "    return obj;\n"
        + "  }\n"
        + "  @Override public void clear(Context context) {\n"
        + "    Utils.getSharedPreferenceEditor(context, \"test.TestTwo\").clear().apply();\n"
        + "  }\n"
        + "}");

      assertAbout(javaSources()).that(asList(source1, source2, source3))
          .withCompilerOptions("-Xlint:-processing")
          .processedWith(new FitProcessor())
          .compilesWithoutWarnings()
          .and()
          .generatesSources(sharedSource1,sharedSource2,sharedSource3);
  }


  @Test public void failsInNonParameterConstructor() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
        + "package test;\n"
        + "import fit.SharedPreferenceAble;\n"
        + "@SharedPreferenceAble public class Test {\n"
        + "    public String aT;\n"
        + "    public Test(String aT) {\n"
        + "        this.aT = aT;\n"
        + "}"
        + "}");

    assertAbout(javaSource()).that(source)
        .processedWith(new FitProcessor())
        .failsToCompile()
        .withErrorContaining(
            "Fit can't use no non-parameter constructor")
        .in(source).onLine(3);
  }
  //
  //@Test public void failsIfInPrivateClass() {
  //  JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
  //      + "package test;\n"
  //      + "import android.view.View;\n"
  //      + "import butterknife.BindView;\n"
  //      + "public class Test {\n"
  //      + "  private static class Inner {\n"
  //      + "    @BindView(1) View thing;\n"
  //      + "  }\n"
  //      + "}"
  //  );
  //
  //  assertAbout(javaSource()).that(source)
  //      .processedWith(new ButterKnifeProcessor())
  //      .failsToCompile()
  //      .withErrorContaining(
  //          "@BindView fields may not be contained in private classes. (test.Test.Inner.thing)")
  //      .in(source).onLine(5);
  //}
  //
  //@Test public void failsIfNotView() {
  //  JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
  //      + "package test;\n"
  //      + "import android.app.Activity;\n"
  //      + "import butterknife.BindView;\n"
  //      + "public class Test extends Activity {\n"
  //      + "  @BindView(1) String thing;\n"
  //      + "}"
  //  );
  //
  //  assertAbout(javaSource()).that(source)
  //      .processedWith(new ButterKnifeProcessor())
  //      .failsToCompile()
  //      .withErrorContaining(
  //          "@BindView fields must extend from View or be an interface. (test.Test.thing)")
  //      .in(source).onLine(5);
  //}
  //
  //@Test public void failsIfInInterface() {
  //  JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
  //      + "package test;\n"
  //      + "import android.view.View;\n"
  //      + "import butterknife.BindView;\n"
  //      + "public interface Test {\n"
  //      + "    @BindView(1) View thing = null;\n"
  //      + "}"
  //  );
  //
  //  assertAbout(javaSource()).that(source)
  //      .processedWith(new ButterKnifeProcessor())
  //      .failsToCompile()
  //      .withErrorContaining(
  //          "@BindView fields may only be contained in classes. (test.Test.thing)")
  //      .in(source).onLine(4);
  //}
  //
  //@Test public void failsIfPrivate() {
  //  JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
  //      + "package test;\n"
  //      + "import android.app.Activity;\n"
  //      + "import android.view.View;\n"
  //      + "import butterknife.BindView;\n"
  //      + "public class Test extends Activity {\n"
  //      + "    @BindView(1) private View thing;\n"
  //      + "}"
  //  );
  //
  //  assertAbout(javaSource()).that(source)
  //      .processedWith(new ButterKnifeProcessor())
  //      .failsToCompile()
  //      .withErrorContaining("@BindView fields must not be private or static. (test.Test.thing)")
  //      .in(source).onLine(6);
  //}
  //
  //@Test public void failsIfStatic() {
  //  JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
  //      + "package test;\n"
  //      + "import android.app.Activity;\n"
  //      + "import android.view.View;\n"
  //      + "import butterknife.BindView;\n"
  //      + "public class Test extends Activity {\n"
  //      + "    @BindView(1) static View thing;\n"
  //      + "}"
  //  );
  //
  //  assertAbout(javaSource()).that(source)
  //      .processedWith(new ButterKnifeProcessor())
  //      .failsToCompile()
  //      .withErrorContaining("@BindView fields must not be private or static. (test.Test.thing)")
  //      .in(source).onLine(6);
  //}
  //
  //@Test public void duplicateBindingFails() throws Exception {
  //  JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
  //      + "package test;\n"
  //      + "import android.app.Activity;\n"
  //      + "import android.view.View;\n"
  //      + "import butterknife.BindView;\n"
  //      + "public class Test extends Activity {\n"
  //      + "    @BindView(1) View thing1;\n"
  //      + "    @BindView(1) View thing2;\n"
  //      + "}"
  //  );
  //
  //  assertAbout(javaSource()).that(source)
  //      .processedWith(new ButterKnifeProcessor())
  //      .failsToCompile()
  //      .withErrorContaining(
  //          "Attempt to use @BindView for an already bound ID 1 on 'thing1'. (test.Test.thing2)")
  //      .in(source).onLine(7);
  //}
  //
  //@Test public void failsOptionalRootViewBinding() throws Exception {
  //  JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
  //      + "package test;\n"
  //      + "import android.content.Context;\n"
  //      + "import android.view.View;\n"
  //      + "import butterknife.OnClick;\n"
  //      + "import butterknife.Optional;\n"
  //      + "public class Test extends View {\n"
  //      + "  @Optional @OnClick void doStuff() {}\n"
  //      + "  public Test(Context context) {\n"
  //      + "    super(context);\n"
  //      + "  }\n"
  //      + "}"
  //  );
  //
  //  assertAbout(javaSource())
  //      .that(source)
  //      .processedWith(new ButterKnifeProcessor())
  //      .failsToCompile()
  //      .withErrorContaining(
  //          "ID-free binding must not be annotated with @Optional. (test.Test.doStuff)")
  //      .in(source)
  //      .onLine(7);
  //}
}
