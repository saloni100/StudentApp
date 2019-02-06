import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salonig.studentdemo.R;
import com.salonig.studentdemo.db.Student;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {





    private Context mContext;
    private List<Student> List;





    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView data;
        public TextView value;


        public MyViewHolder(View view) {
            super(view);
//            data = (TextView) view.findViewById(R.id.data);
//            value = (TextView) view.findViewById(R.id.value);

        }
    }

    public Adapter(Context mContext, List<Student> List) {
        this.mContext = mContext;
        this.List = List;
    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item,null);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return 0;
    }
}