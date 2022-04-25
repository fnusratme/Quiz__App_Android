package my.first.a3_QuizApp_Android_fnusrat;

import java.util.ArrayList;
public class QuestionBank {
    ArrayList<QuesandAns> questionList = new ArrayList<>();
   ArrayList<Integer> colorList = new ArrayList<>();


    QuestionBank(){
        QuesandAns ques1 = new QuesandAns(R.string.Question1,false);
        QuesandAns ques2 = new QuesandAns(R.string.Question2,true);
        QuesandAns ques3 = new QuesandAns(R.string.Question3,true);
        QuesandAns ques4 = new QuesandAns(R.string.Question4,true);
        QuesandAns ques5 = new QuesandAns(R.string.Question5,true);
        QuesandAns ques6 = new QuesandAns(R.string.Question6,false);
        QuesandAns ques7 = new QuesandAns(R.string.Question7,false);
        QuesandAns ques8 = new QuesandAns(R.string.Question8,false);
        QuesandAns ques9 = new QuesandAns(R.string.Question9,false);
        QuesandAns ques10 = new QuesandAns(R.string.Question10,false);

        questionList.add(ques1);
        questionList.add(ques2);
        questionList.add(ques3);
        questionList.add(ques4);
        questionList.add(ques5);
        questionList.add(ques6);
        questionList.add(ques7);
        questionList.add(ques8);
        questionList.add(ques9);
        questionList.add(ques10);

        colorList.add(R.color.Brown);
        colorList.add(R.color.Aqua);
        colorList.add(R.color.Orchid);
        colorList.add(R.color.Aquamarine);
        colorList.add(R.color.CadetBlue);
        colorList.add(R.color.DarkKhaki);
        colorList.add(R.color.DarkMagenta);
        colorList.add(R.color.IndianRed);
        colorList.add(R.color.LightSalmon);
        colorList.add(R.color.HotPink);




    }

}
