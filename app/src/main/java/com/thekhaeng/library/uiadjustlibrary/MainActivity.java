package com.thekhaeng.library.uiadjustlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    public static final String KEY_THEME = "key_theme";
    public static final int THEME_1 = 1;
    public static final int THEME_2 = 2;
    public static final int THEME_3 = 3;
    private View fab;
    private boolean isRestart = false;

    @Override
    protected void onCreate( Bundle savedInstanceState ){
        Bundle bundle = getIntent().getExtras();
        if( bundle != null ){
            isRestart = true;
            int theme = bundle.getInt( KEY_THEME, -1 );
            switch( theme ){
                case THEME_1:
                    setTheme( R.style.AppTheme );
                    break;
                case THEME_2:
                    setTheme( R.style.AppTheme1 );
                    break;
                case THEME_3:
                    setTheme( R.style.AppTheme2 );
                    break;
                default:
                    isRestart = false;
            }
        }
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        fab = findViewById( R.id.fab_adjust );

        if( BuildConfig.DEBUG ){
            UIAdjustMainActivity
                    .create( this, fab )
                    .showKeepActivityGlobalSetting( (TextView) findViewById( R.id.tv_keep_activity ) )
                    .setUseLocalStorage( true, true );

        }

    }

    public void restart( int theme ){
        if( isRestart ){
            isRestart = false;
            return;
        }
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putInt( KEY_THEME, theme );
        intent.putExtras( bundle );
        finish();
        startActivity( intent );
    }
}
