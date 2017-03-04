package bpr10.git.lintrules;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.utils.SdkUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;

public abstract class AbstractDetectorTest extends LintDetectorTest {

  protected static final String PATH_TEST_RESOURCES = "/src/test/resources";

  @Override
  protected boolean allowCompilationErrors() {
    return true;
  }

  @Override
  protected InputStream getTestResource(String relativePath, boolean expectExists) {
    String path = (PATH_TEST_RESOURCES + File.separatorChar +
        relativePath).replace('/', File.separatorChar);
    File file = new File(getTestDataRootDir(), path);
    if (file.exists()) {
      try {
        return new BufferedInputStream(new FileInputStream(file));
      } catch (FileNotFoundException e) {
        if (expectExists) {
          fail("Could not find file " + relativePath);
        }
      }
    }
    return null;
  }

  private File getTestDataRootDir() {
    CodeSource source = getClass().getProtectionDomain().getCodeSource();
    if (source != null) {
      URL location = source.getLocation();
      try {
        File classesDir = SdkUtils.urlToFile(location);
        return classesDir.getParentFile().getAbsoluteFile().getParentFile().getParentFile();
      } catch (MalformedURLException e) {
        fail(e.getLocalizedMessage());
      }
    }
    return null;
  }

}
