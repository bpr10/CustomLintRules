package bpr10.git.lintrules;

import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.TextFormat;

import java.util.Collections;
import java.util.List;

/**
 * @author: bedprakash on 28/2/17.
 */

public class CustomViewDetectorTest extends AbstractDetectorTest {
  @Override
  protected Detector getDetector() {
    return new CustomViewAndAttributeDetector();
  }

  @Override
  protected List<Issue> getIssues() {
    return Collections.singletonList(CustomViewAndAttributeDetector.ISSUE);
  }

  public void testShouldNotAccept() throws Exception {
    String result = lintFiles("/res/layout/incorrect_layout.xml");
    assertTrue(
        result.contains(CustomViewAndAttributeDetector.ISSUE.getExplanation(TextFormat.TEXT)));
  }

  public void testShouldAccept() throws Exception {
    String expected = "No warnings.";
    String result = lintFiles("/res/layout/correct_layout.xml");
    assertEquals(expected, result);
  }
}
