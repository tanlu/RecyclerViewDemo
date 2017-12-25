package com.gjl.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private RecyclerView recyclerView;
	private ArrayList<Integer> imgList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//recyclerview的使用
		recyclerView = (RecyclerView) findViewById(R.id.myrecycle);
		//recyclerview 相对比较简单，给了开发者较大的自定义控件，向对的，需要我们写的东邪也多了
		//有一个比较重要的东西就是布局管理器
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		//这里LayoutManager可以设置方向
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(linearLayoutManager);
		ArrayList<String> list=  initData();
		recyclerView.setAdapter(new MyRecycleAdapter(list));
		Log.d(TAG, "onCreate() returned: " + "设置适配器");
	}
	//初始化数据
	private ArrayList<String> initData() {
		int[] ids = new int[]{
				R.drawable.aaaa,
				R.drawable.abc,
				R.drawable.f
		};
		imgList = new ArrayList<>();
		ArrayList<String> list = new ArrayList<>();
		for (int i=0;i<50;i++){

			list.add("杨过"+i);

			int j = i%ids.length;
			imgList.add(ids[j]);

		}
		return list;
	}
	//设置适配器。注意这里的适配器不一样。
	class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ViewHoder>{
		private final ArrayList<String> list;

		public MyRecycleAdapter(ArrayList<String> list){
			this.list = list;
		}
		//当创建ViewHolder的时候
		@Override
		public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
			ViewHoder viewHoder = new ViewHoder(view);
			return viewHoder;
		}
		//当关联ViewHolder的时候
		@Override
		public void onBindViewHolder(ViewHoder holder, int position) {

			holder.getTextView().setText(list.get(position));
			holder.getImageView().setImageResource(imgList.get(position));
		}

		@Override
		public int getItemCount() {
			return list.size();
		}

		//这里RecyclerView强制是用ViewHoler进行回收
		class ViewHoder extends RecyclerView.ViewHolder{
			private TextView textView;
			private ImageView imageView;

			public ViewHoder(View itemView) {
				super(itemView);//继承ViewHolder，并重写父类方法
				this.textView = itemView.findViewById(R.id.item_tv);
				this.imageView = itemView.findViewById(R.id.image);
			}

			public TextView getTextView() {
				return textView;
			}

			public void setTextView(TextView textView) {
				this.textView = textView;
			}

			public ImageView getImageView() {
				return imageView;
			}

			public void setImageView(ImageView imageView) {
				this.imageView = imageView;
			}
		}

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu1,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId){
			case R.id.one:
				Log.d(TAG, "onOptionsItemSelected() returned:  one" );
				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
				recyclerView.setLayoutManager(linearLayoutManager);
				break;
			case R.id.two:
				GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
				recyclerView.setLayoutManager(gridLayoutManager);
				break;
			case R.id.three:
				StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
				recyclerView.setLayoutManager(staggeredGridLayoutManager);
				break;
		}
		return true;
	}
}
