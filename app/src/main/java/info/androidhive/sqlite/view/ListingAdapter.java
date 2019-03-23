package info.androidhive.sqlite.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import info.androidhive.sqlite.R;
import info.androidhive.sqlite.database.model.Listing;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.MyViewHolder> {

    private Context context;
    private List<Listing> listingList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView student;
        public TextView dot;
        public TextView priority;
        public TextView student_class;

        public MyViewHolder(View view) {
            super(view);
            student = view.findViewById(R.id.note);
            dot = view.findViewById(R.id.dot);
            priority = view.findViewById(R.id.timestamp);
            student_class = view.findViewById(R.id.student_class);
        }
    }


    public ListingAdapter(Context context, List<Listing> listingList) {
        this.context = context;
        this.listingList = listingList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wait_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Listing list = listingList.get(position);

        holder.student.setText(list.getStudent());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.priority.setText(list.getPriority());

        holder.student_class.setText(list.getCourse());

    }

    @Override
    public int getItemCount() {
        return listingList.size();
    }
}
