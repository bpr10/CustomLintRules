package git.bpr10.toastdetectorlint;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Demo.showToast(this, "demo", Toast.LENGTH_LONG);
    Toast.makeText(this, "demo", Toast.LENGTH_LONG);
  }

  /**
   * custom toast view for displaying exit message by selected language.
   */
  class Demo {
    public static void showToast(Context context, String exitMessage, int displayTime) {
      if (null == context) {
        return;
      }
      Toast toast = Toast.makeText(context, exitMessage, displayTime);
      View view = toast.getView();
      TextView textView = (TextView) view.findViewById(android.R.id.message);
      textView.setPadding(0, 10, 0, 10);
      textView.setText(exitMessage);
      toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 10, 30);
      toast.show();
    }
  }
}
