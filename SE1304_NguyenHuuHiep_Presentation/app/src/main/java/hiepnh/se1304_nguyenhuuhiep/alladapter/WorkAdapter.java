package hiepnh.se1304_nguyenhuuhiep.alladapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.dtos.WorkingDTO;

public class WorkAdapter extends BaseAdapter {

    private List<WorkingDTO> workingDTOList;
    @Override
    public int getCount() {
        return workingDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return workingDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return workingDTOList.indexOf(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.item, viewGroup, false);
        }
        WorkingDTO dto = workingDTOList.get(i);
        TextView txtName = view.findViewById(R.id.txtName);
//        TextView txtMark = view.findViewById(R.id.txtMark);
//        TextView txtName = view.findViewById(R.id.txtName);
        txtName.setText(dto.getName());
        return view;
    }

    public void setWorkingDTOList(List<WorkingDTO> workingDTOList) {
        this.workingDTOList = workingDTOList;
    }
}
