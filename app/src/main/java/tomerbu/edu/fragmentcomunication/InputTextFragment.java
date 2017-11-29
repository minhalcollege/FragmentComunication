package tomerbu.edu.fragmentcomunication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputTextFragment extends Fragment implements TextWatcher {

    private EditText etInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //findViewById is different in fragments: we need to put some extra work:
        View v = inflater.inflate(R.layout.fragment_input_text, container, false);
        etInput = v.findViewById(R.id.etInput);
        etInput.addTextChangedListener(this);

        //Fragments are light! no Context, No Window. but fragments have an activity
//        Toast.makeText(getActivity(), "!", Toast.LENGTH_SHORT).show();
        return v;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    //declare a new interface. used to notify listeners (communication)
    public interface OnTextChangedListener {
        void onTextChanged(String text);
    }
    //declare that we have a listener of type: OnTextChangedListener
    private OnTextChangedListener listener;//onAttach!


    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (listener != null)
            listener.onTextChanged(charSequence.toString());
    }
    //once connected:
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity instanceof OnTextChangedListener){
            this.listener = (OnTextChangedListener) activity;
        }
    }
    //extra for memory efficiency
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
}


