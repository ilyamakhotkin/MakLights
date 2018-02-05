package MakLights;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

public class Alarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
    }

    public void ActionOnButton(View view){
        TimePicker timePicker = findViewById(R.id.timePicker);
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        String message = "SET_ALARM;";
        message = message.concat(String.valueOf(hour));
        message = message.concat(":");
        message = message.concat(String.valueOf(minute));
        Communicator.communicator.send(message);
    }
}
