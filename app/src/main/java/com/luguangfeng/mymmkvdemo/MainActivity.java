package com.luguangfeng.mymmkvdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.luguangfeng.mymmkvdemo.databinding.ActivityMainBinding;
import com.luguangfeng.mymmkvdemo.util.IMuteType;
import com.luguangfeng.mymmkvdemo.viewmodel.MainViewModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Used to load the 'mymmkvdemo' library on application startup.
    static {
        System.loadLibrary("mymmkvdemo");
    }

    private ActivityMainBinding binding;

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initListeners();
        initData();
    }

    private void initListeners() {
        binding.btnWrite.setOnClickListener(this);
        binding.btnRead.setOnClickListener(this);
        binding.btnDelete.setOnClickListener(this);
    }

    private void initData() {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.init(this);
        mViewModel.getLogsLiveData().observe(this, this::setUserLogs);
        mViewModel.getRootDirLiveData().observe(this, binding.tvMmkvRootDir::setText);
    }

    private void setUserLogs(ArrayList<String> logs) {
        StringBuilder sb = new StringBuilder();
        for (String logStr : logs) {
            sb.append(logStr).append("\n");
        }
        binding.tvLog.setText(sb.toString());
        binding.svLogContainer.post(() -> binding.svLogContainer.fullScroll(View.FOCUS_DOWN));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_test:
                mViewModel.handleBtnWriteTest1000Times();
                break;
            case R.id.menu_sp:
                mViewModel.setMenuType(IMuteType.MENU_TYPE_SP);
                break;
            case R.id.menu_mmkv:
                mViewModel.setMenuType(IMuteType.MENU_TYPE_MMKV);
                break;
            case R.id.menu_my_kv:
                mViewModel.setMenuType(IMuteType.MENU_TYPE_MY_KV);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        String key = getKeyInputNow();
        if (view == binding.btnWrite) {
            mViewModel.handleBtnWriteClick(key, getValueInputNow());
        } else if (view == binding.btnRead) {
            mViewModel.handleBtnReadClick(key);
        } else if (view == binding.btnDelete) {
            mViewModel.handleBtnDeleteClick(key);
        }
    }

    private String getKeyInputNow() {
        return binding.etKey.getText().toString();
    }

    private String getValueInputNow() {
        return binding.etValue.getText().toString();
    }
}