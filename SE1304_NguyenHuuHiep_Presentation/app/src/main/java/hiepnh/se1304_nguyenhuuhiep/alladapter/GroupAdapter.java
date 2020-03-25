package hiepnh.se1304_nguyenhuuhiep.alladapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hiepnh.se1304_nguyenhuuhiep.R;
import hiepnh.se1304_nguyenhuuhiep.dtos.GroupDTO;

public class GroupAdapter extends BaseAdapter {

    private List<GroupDTO> groupDTOList;
    @Override
    public int getCount() {
        return groupDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return groupDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return groupDTOList.indexOf(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.item, viewGroup, false);
        }
        GroupDTO dto = groupDTOList.get(i);
        TextView txtName = view.findViewById(R.id.txtName);
//        TextView txtMark = view.findViewById(R.id.txtMark);
//        TextView txtName = view.findViewById(R.id.txtName);
        txtName.setText(dto.getGroupName());
        return view;
    }

    public void setGroupDTOList(List<GroupDTO> groupDTOList) {
        this.groupDTOList = groupDTOList;
    }
}
