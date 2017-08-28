package com.example.themsteam.itweekdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.themsteam.itweekdemo.R;
import com.example.themsteam.itweekdemo.data.QRCodeDatabase;
import com.example.themsteam.itweekdemo.data.QRCodeModel;
import com.example.themsteam.itweekdemo.ui.AchievementSnackbar;
import com.example.themsteam.itweekdemo.ui.ErrorBundle;
import com.example.themsteam.itweekdemo.ui.IQRCodeListView;
import com.example.themsteam.itweekdemo.ui.adapter.QRCodeListAdapter;
import com.example.themsteam.itweekdemo.ui.presenter.IListPresenter;
import com.example.themsteam.itweekdemo.ui.presenter.QRCodeListPresenter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IQRCodeListView {

    @BindView(R.id.fabAddQRCode)
    FloatingActionButton fabAddQRCode;

    @BindView(R.id.rvQRCodes)
    RecyclerView rvQRCodes;

    @BindView(R.id.clRoot)
    CoordinatorLayout clRoot;

    @BindView(R.id.tvHint)
    TextView tvHint;

    AchievementSnackbar snackbar;

    QRCodeListAdapter adapter;

    IListPresenter presenter;

    @OnClick(R.id.fabAddQRCode)
    public void showQRCodeScan() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new QRCodeListAdapter();
        presenter = new QRCodeListPresenter(this, QRCodeDatabase.getInstance(), new ErrorBundle(this));
        rvQRCodes.setAdapter(adapter);

        checkHintVisibility(presenter.getQRCodes());

        adapter.setQrCodes(presenter.getQRCodes());
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<QRCodeModel> qrCodeModels = presenter.getQRCodes();
        if (presenter.isAllItemsScanned(qrCodeModels)) {
            showScanCompleted(new ArrayList<>(qrCodeModels));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }

    private void checkHintVisibility(List<QRCodeModel> codes) {
        if (codes.size() == 0) {
            tvHint.setVisibility(View.VISIBLE);
            rvQRCodes.setVisibility(View.GONE);
        } else {
            tvHint.setVisibility(View.GONE);
            rvQRCodes.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                presenter.proceedNewQRCode(result.getContents());
            }
        }
    }

    public void showScanSuccess(List<QRCodeModel> qrCodes) {
        adapter.setQrCodes(qrCodes);
        checkHintVisibility(qrCodes);
        Snackbar.make(fabAddQRCode, getString(R.string.scan_success), Snackbar.LENGTH_LONG).show();
    }

    public void showScanError(String errorMessage) {
        Snackbar.make(fabAddQRCode, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    public void showScanCompleted(List<QRCodeModel> qrCodes) {
        adapter.setQrCodes(qrCodes);
        checkHintVisibility(qrCodes);
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(() -> {
                    snackbar = AchievementSnackbar.make(clRoot, AchievementSnackbar.LENGTH_INDEFINITE);
                    snackbar.show();
                    snackbar.setAction(view -> presenter.shareLink());
        }
        , 500);
    }

    @Override
    public void shareLink(String link) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, link);
        sendIntent.setType(getString(R.string.plain_text));
        startActivity(Intent.createChooser(sendIntent, getString(R.string.send_to)));
    }
}
