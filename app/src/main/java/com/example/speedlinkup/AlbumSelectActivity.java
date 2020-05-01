package com.example.speedlinkup;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.yanzhenjie.album.Album;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AlbumSelectActivity extends Activity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int limitCount = 1;
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        String type = sp.getString("type", "full");
        String size = sp.getString("size", "middle");
        if (type.equals("full")) {
            if (size.equals("small")) {
                limitCount = 27;
            }
            if (size.equals("middle")) {
                limitCount = 44;
            }
            if (size.equals("large")) {
                limitCount = 65;
            }
        } else if (type.equals("heart")) {
            if (size.equals("small")) {
                limitCount = 12;
            }
            if (size.equals("middle")) {
                limitCount = 22;
            }
            if (size.equals("large")) {
                limitCount = 34;
            }
        } else if (type.equals("face")) {
            if (size.equals("small")) {
                limitCount = 23;
            }
            if (size.equals("middle")) {
                limitCount = 36;
            }
            if (size.equals("large")) {
                limitCount = 48;
            }
        }
        Album.startAlbum(this, 100,limitCount);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) { // 判断是否成功。
                // 拿到用户选择的图片路径List：
                List<String> pathList = Album.parseResult(data);
                Set<String> stringSet = new TreeSet<>(pathList);
                SharedPreferences.Editor editor=sp.edit();
                editor.putStringSet("pathSet",stringSet);
                editor.apply();
                finish();
            } else if (resultCode == RESULT_CANCELED) { // 用户取消选择。
                Toast.makeText(this, "取消了图片选择", Toast.LENGTH_SHORT).show();
                finish();
                // 根据需要提示用户取消了选择。
            }
        }
    }
}
