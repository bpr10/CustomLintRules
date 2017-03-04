package bpr10.git.lintrules;

/**
 * @author: bedprakash on 30/1/17.
 */

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.TextFormat;

import java.util.Collections;
import java.util.List;

import lombok.ast.AstVisitor;
import lombok.ast.MethodDeclaration;
import lombok.ast.MethodInvocation;
import lombok.ast.Node;

/**
 * Detector looking for Toast.makeText() outside showToast() which is an utility
 * to manage text gravity in toast.
 */
public class DirectToastDetector extends Detector implements Detector.JavaScanner {

  private static final String LOG_TAG = DirectToastDetector.class.getSimpleName();

  //Issue discovered by this detector
  public static final Issue ISSUE = Issue.create(
      "ToastUsedDirectly",
      "Toast called outside showToast",
      "`Toast` should be called from `showToast()` to ensure formatted Toast",
      Category.CORRECTNESS,
      6,
      Severity.ERROR,
      new Implementation(
          DirectToastDetector.class,
          Scope.JAVA_FILE_SCOPE));

  @Override
  public List<String> getApplicableMethodNames() {
    return Collections.singletonList("makeText");
  }

  @Override
  public void visitMethod(JavaContext context, AstVisitor visitor, MethodInvocation node) {
    // "makeText()" in the code with no operand
    if (node.astOperand() == null) {
      return;
    }
    String operand = node.astOperand().toString();
    if (!(operand.equals("Toast") || operand.endsWith(".Toast"))) {
      return;
    }
    Node method = JavaContext.findSurroundingMethod(node.getParent());
    if (method instanceof MethodDeclaration &&
        !((MethodDeclaration) method).astMethodName().astValue().equals("showToast")) {
      context.report(ISSUE, method, context.getLocation(method),
          ISSUE.getExplanation(TextFormat.TEXT));
    }
  }
}