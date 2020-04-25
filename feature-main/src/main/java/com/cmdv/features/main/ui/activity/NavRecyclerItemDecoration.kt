package com.cmdv.features.main.ui.activity

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cmdv.features.R

class NavRecyclerItemDecoration(private val context: Context): RecyclerView.ItemDecoration() {
	private var divider: Drawable = ContextCompat.getDrawable(context, R.drawable.divider_nav_recycler)!!

	override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
		val left = parent.paddingLeft + 60
		val right = parent.width - parent.paddingRight - 40

		for (i in 0 until parent.childCount) {
			if (i == 3) {
				val child: View = parent.getChildAt(i)

				val params: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams

				val top = child.bottom + params.bottomMargin
				val bottom = top + divider.intrinsicHeight

				divider.setBounds(left, top, right, bottom)
				divider.draw(c)
			}
		}
	}
}

/*


public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public SimpleDividerItemDecoration(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.line_divider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}


 */