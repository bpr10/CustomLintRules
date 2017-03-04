package git.bpr10.toastdetectorlint;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toast.makeText(this, "demo", Toast.LENGTH_LONG);
    Toast.makeText(this, "demo", Toast.LENGTH_LONG).show();
    showToast(this, "Demo", Toast.LENGTH_LONG);

    //TODO: dejoieodm  djeifnew
    EnumTest firstDay = new EnumTest(Day.MONDAY);
    firstDay.tellItLikeItIs();
    EnumTest thirdDay = new EnumTest(Day.WEDNESDAY);
    thirdDay.tellItLikeItIs();
    EnumTest fifthDay = new EnumTest(Day.FRIDAY);
    fifthDay.tellItLikeItIs();
    EnumTest sixthDay = new EnumTest(Day.SATURDAY);
    sixthDay.tellItLikeItIs();
    EnumTest seventhDay = new EnumTest(Day.SUNDAY);
    seventhDay.tellItLikeItIs();
  }


  class EnumTest {
    Day day;

    public EnumTest(Day day) {
      this.day = day;
    }

    public void tellItLikeItIs() {
      switch (day) {
        case MONDAY:
          System.out.println("Mondays are bad.");
          break;

        case FRIDAY:
          System.out.println("Fridays are better.");
          break;

        case SATURDAY: case SUNDAY:
          System.out.println("Weekends are best.");
          break;

        default:
          System.out.println("Midweek days are so-so.");
          break;
      }
    }

  }
  /**
   * custom toast view for displaying exit message by selected language.
   */
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
