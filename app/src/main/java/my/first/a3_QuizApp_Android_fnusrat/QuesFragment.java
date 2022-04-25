package my.first.a3_QuizApp_Android_fnusrat;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuesFragment extends Fragment {
    TextView quesText;
    int question;
    int color;

    public static QuesFragment newInstance(int quesID, int colorId) {
        
        Bundle args = new Bundle();
        args.putInt("QuestionId",quesID);
        args.putInt("ColorId",colorId);
        QuesFragment fragment = new QuesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //   Inflating layout for fragment
       View view=  inflater.inflate(R.layout.fragment_question, container, false);
        quesText = (TextView) view.findViewById(R.id.quesText);
        question = getArguments().getInt("QuestionId");
        color = getArguments().getInt("ColorId");
        quesText.setText(question);
        quesText.setBackgroundResource(color);
        return view;
    }
    
}