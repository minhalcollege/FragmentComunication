package tomerbu.edu.fragmentcomunication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HostActivity extends AppCompatActivity implements InputTextFragment.OnTextChangedListener {

    @Override
    public void onTextChanged(String text) {
        //give the text to the chat... fragment communication:
        //1) obtain a reference to ChatFragment:
        //in android: we can find a fragment by tag:... FragmentManager.

        ChatFragment chat = (ChatFragment) getSupportFragmentManager().findFragmentByTag("chat");
        //TODO: How to give the fragments id?
        if (chat == null) return;
        //2) call a method on it.TODO: let them implement the interface as well.
        chat.onTextChanged(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();
    }

    private void initUI() {
        //1) new InputTextFragment(), new ChatFragment()...
        InputTextFragment inFrag = new InputTextFragment();
        ChatFragment chatFrag = new ChatFragment();

        //2) Using a fragment transaction:
        //into frame1 -> InputTextFragment
        //into frame2 -> ChatFragment
        //SupportFragmentManager.beginTransaction
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame1, inFrag, "input")
                .replace(R.id.frame2, chatFrag, "chat")
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_host, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
