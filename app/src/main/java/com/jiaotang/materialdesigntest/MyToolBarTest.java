package com.jiaotang.materialdesigntest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**控件：用到了toolbar（标题栏）和navigationView（侧滑栏）
 * 菜单：标题栏使用toolbar.xml ;  侧滑栏用了nav_menu.xml
 * 布局：侧滑栏用了nav_header.xml*/

public class MyToolBarTest extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tool_bar_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);//侧滑导航栏
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        /**recycleView相关代码*/
        initFruit();//初始化数据
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);//定义recycleView布局方式
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        /**下拉刷新控件设置*/
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruit();//自定义方法，刷新时的具体操作
            }
        });

        /**标题栏相关设置*/
        setSupportActionBar(toolbar);//加载toolbar
        ActionBar actionBar = getSupportActionBar();//获取标题栏实例

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//设置标题栏左上角的导航按钮图标可显示
            actionBar.setTitle("可改变的标题");//设置标题，默认为项目名称，也可以在配置文件了改动
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_camera);//可选，设置导航按钮图标。默认图标为一个箭头
        }


        /**侧滑导航栏相关设置*/
        nav_view.setCheckedItem(R.id.camera);//设置默认选中菜单项
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /**设置侧滑导航栏菜单项点击事件*/
                switch (item.getItemId()) {
                    case R.id.camera:
                        Toast.makeText(MyToolBarTest.this,"点击了camera",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.gallery:
                        Toast.makeText(MyToolBarTest.this,"点击了gallery",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.manage:
                        Toast.makeText(MyToolBarTest.this,"点击了manage",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.send:
                        Toast.makeText(MyToolBarTest.this,"点击了send",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.share:
                        Toast.makeText(MyToolBarTest.this,"点击了share",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }


                return true;
            }
        });

    }

    private void refreshFruit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fruitList.clear();
                        initFruit();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruit(){
        Fruit[] fruits = {new Fruit("fruit1",R.drawable.fruit1)
                ,new Fruit("fruit2",R.drawable.fruit2),new Fruit("fruit3",R.drawable.fruit3)
                ,new Fruit("fruit4",R.drawable.fruit4),new Fruit("fruit5",R.drawable.fruit5)};
        for (int i=0; i<50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }

    }

    @Override/**创建toolbar标题栏菜单*/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override/**设置标题栏菜单项点击事件*/
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this,"点击了backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"点击了delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"点击了setting",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home://设置左上角导航按钮
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
                break;
        }

        return true;
    }

    /**
     * 悬浮按钮点击事件
     */
    public void clickFloatingButton(View view) {

        Snackbar.make(view,"是否删除数据",Snackbar.LENGTH_SHORT).setAction("删除？", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyToolBarTest.this,"数据已删除",Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
