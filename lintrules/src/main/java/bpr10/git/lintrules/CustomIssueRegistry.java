package bpr10.git.lintrules;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

/**
 * @author: bedprakash on 30/1/17.
 */
@SuppressWarnings("unused")
public class CustomIssueRegistry extends IssueRegistry {

  private List<Issue> mIssues = Arrays.asList(
      DirectToastDetector.ISSUE,
      CustomViewAndAttributeDetector.ISSUE
  );

  @Override
  public List<Issue> getIssues() {
    return mIssues;
  }
}
