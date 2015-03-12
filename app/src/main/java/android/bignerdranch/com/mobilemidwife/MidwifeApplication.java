package android.bignerdranch.com.mobilemidwife;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by michaelcabus on 3/11/15.
 */
public class MidwifeApplication extends Application {
// Enable Local Datastore.
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "hHo3hJNJ0Aw6q0wNq6R2RLsCjFiTiwDR4uCMnmmi", "OdQ23t2bBe82rdj4mEoubBbYpGyxwAv5cR0QISvm");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();


    }

}
