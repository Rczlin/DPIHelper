package cn.rlint.dpihelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private int originalDpi = 0;
    private int currentDpi = 0;
    private static final String DPI_FILE_NAME = "original_dpi.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        EditText editDpi = findViewById(R.id.editDpi);
        Button btnSetDpi = findViewById(R.id.btnSetDpi);
        Button btnRestoreDpi = findViewById(R.id.btnRestoreDpi);
        TextView txtRestoreInfo = findViewById(R.id.txtRestoreInfo);
        currentDpi = GetCurrentDpi();
        editDpi.setText(String.valueOf(currentDpi));
        originalDpi = ReadOriginalDpiFromFile();
        if (originalDpi == -1) {
            originalDpi = currentDpi;
            SaveOriginalDpiToFile(originalDpi);
        }
        txtRestoreInfo.setText("原始 DPI：" + originalDpi + "  其实你首次打开是多少就是多少");

        btnSetDpi.setOnClickListener(v -> {
            String dpiStr = editDpi.getText().toString();
            if (!dpiStr.isEmpty()) {
                int newDpi = Integer.parseInt(dpiStr);
                SetDpi(newDpi,true);
                int updatedDpi = GetCurrentDpi();
                editDpi.setText(String.valueOf(updatedDpi));
            }
        });

        btnRestoreDpi.setOnClickListener(v -> {
            SetDpi(originalDpi,false);
            int updatedDpi = GetCurrentDpi();
            editDpi.setText(String.valueOf(updatedDpi));
        });
    }

    private int GetCurrentDpi() {
        try {
            return Settings.Secure.getInt(getContentResolver(), "display_density_forced");
        } catch (Settings.SettingNotFoundException e) {
            return 320; // 哎呦不中嘞 320启动
        }
    }

    private void SaveOriginalDpiToFile(int dpi) {
        try {
            FileOutputStream fos = openFileOutput(DPI_FILE_NAME, MODE_PRIVATE);
            fos.write((dpi + "").getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int ReadOriginalDpiFromFile() {
        try {
            File file = new File(getFilesDir(), DPI_FILE_NAME);
            if (!file.exists()) return -1;
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            fis.close();
            return Integer.parseInt(new String(buffer));
        } catch (Exception e) {
            return -1;
        }
    }

    private void SetDpi(int dpi,boolean needsure) {
        if(needsure){
            new AlertDialog.Builder(this)
                    .setTitle("确认更改 DPI")
                    .setMessage("你确定要将 DPI 设置为 " + dpi + " 吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Settings.Secure.putInt(getContentResolver(), "display_density_forced", dpi);
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }else {
            Settings.Secure.putInt(getContentResolver(), "display_density_forced", dpi);
        }
    }
}