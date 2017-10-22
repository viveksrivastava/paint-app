package viveksrivastava.github.io.paint;

/**
 * Created by User on 19-10-2017.
 */

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {

    private PaintView paintView;
    private static final int DIALOG_ID = 0;
    private static final int DIALOG_ID1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, new ExamplePreferenceFragment())
                    .commit();
        }
*/
        setContentView(R.layout.activity_main);
        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal:
                paintView.normal();
                return true;
            case R.id.emboss:
                paintView.emboss();
                return true;
            case R.id.blur:
                paintView.blur();
                return true;
            case R.id.clear:
                paintView.clear();
                return true;
            case R.id.undo:
                paintView.undo();
                return true;
            case R.id.menu_color_picker_dialog:
                ColorPickerDialog.newBuilder()
                        .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                        .setAllowPresets(false)
                        .setDialogId(DIALOG_ID)
                        .setColor(Color.BLACK)
                        .setShowAlphaSlider(true)
                        .show(this);
                return true;
            /*case R.id.menu_fill_color_picker_dialog:
                ColorPickerDialog.newBuilder()
                        .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                        .setAllowPresets(false)
                        .setDialogId(DIALOG_ID1)
                        .setColor(Color.BLACK)
                        .setShowAlphaSlider(true)
                        .show(this);
                return true;*/
            case R.id.menu_github:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/viveksrivastava/paint-app")));
                } catch (ActivityNotFoundException ignored) {
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override public void onColorSelected(int dialogId, int color) {
        switch (dialogId) {
            case DIALOG_ID:
                // We got result from the dialog that is shown when clicking on the icon in the action bar.
                paintView.setCurrentColor(color);
                Toast.makeText(MainActivity.this, "Selected Color: #" + Integer.toHexString(color), Toast.LENGTH_SHORT).show();
                break;
            /*case DIALOG_ID1:
                // We got result from the dialog that is shown when clicking on the icon in the action bar.
                paintView.fill(color);
                Toast.makeText(MainActivity.this, "Selected Fill Color: #" + Integer.toHexString(color), Toast.LENGTH_SHORT).show();
                break;*/
        }
    }

    @Override public void onDialogDismissed(int dialogId) {

    }


}