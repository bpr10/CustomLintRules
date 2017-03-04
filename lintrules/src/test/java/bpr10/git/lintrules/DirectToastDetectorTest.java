package bpr10.git.lintrules;

import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.TextFormat;

import java.util.Collections;
import java.util.List;

/**
 * @author: bedprakash on 6/2/17.
 */

public class DirectToastDetectorTest extends AbstractDetectorTest {

  @Override
  protected Detector getDetector() {
    return new DirectToastDetector();
  }

  @Override
  protected List<Issue> getIssues() {
    return Collections.singletonList(DirectToastDetector.ISSUE);
  }


  public void testShouldNotAccept() throws Exception {
    String result = lintFiles("/main/java/directToast/IncorrectMainActivity.java");
    assertTrue(result.contains(DirectToastDetector.ISSUE.getExplanation(TextFormat.TEXT)));
  }

  public void testShouldAccept() throws Exception {
    String expected = "No warnings.";
    String result = lintFiles("/main/java/directToast/CorrectMainActivity.java");
    assertEquals(expected, result);
  }
}
