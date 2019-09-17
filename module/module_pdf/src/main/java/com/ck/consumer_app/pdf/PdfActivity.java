package com.ck.consumer_app.pdf;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ck.consumer_app.pdf.pdfview.PDFView;
import com.ck.consumer_app.pdf.pdfview.listener.OnPageChangeListener;
import com.code.utils.RouterPath;

@Route(path = RouterPath.path_pdf_activity)
public class PdfActivity extends AppCompatActivity {

    public String path = "https://storage.googleapis.com/applied-dl/SciPy_Tokyo.pdf";
    private PDFView mPdfView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        initView();
    }

    private void initView() {
        mPdfView = (PDFView) findViewById(R.id.pdfView);

        // 在我这个测试例子中，事先准备一个叫做sample.pdf的pdf大文件放到assets目录下。
        // 从assets文件目录下读取名为 sample.pdf的文件，缺省把该pdf定位到第一页。
        mPdfView.fromAsset("SciPy_Tokyo.pdf").defaultPage(1).onPageChange(new OnPageChangeListener() {

            @Override
            public void onPageChanged(int page, int pageCount) {
                // 当用户在翻页时候将回调。
                Toast.makeText(getApplicationContext(), page + " / " + pageCount, Toast.LENGTH_SHORT).show();
            }
        }).load();
    }
}
