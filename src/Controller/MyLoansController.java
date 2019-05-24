package Controller;

import Models.Entities.LoanModel;
import UI.Views.MessageView;
import UI.Views.MyLoansView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyLoansController {
    private MyLoansView view;
    private LoanModel loanModel;
    private ArrayList<LoanModel> listOfLoans;

    public MyLoansController(MyLoansView view, ArrayList<LoanModel> listOfLoans){
        this.listOfLoans = listOfLoans;
        this.view = view;

        this.view.getBottomToolbar().addReturnLoanButtonListener(new ReturnButtonListener());
    }

    class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getTable().getSelectionModel().isSelectionEmpty()) {
                MessageView error = new MessageView("You must select an item to return!");
            }
            else {
                int row = view.getTable().getSelectedRow();
                int column = 0;

                int loanId = Integer.parseInt(view.getTable().getValueAt(row, column).toString());
                loanModel = getLoanWith(loanId);
                loanModel.returnLoan();
            }
        }
    }

    public LoanModel getLoanWith(int loanId) {
        for (LoanModel loan : listOfLoans) {
            if (loan.getLoanId() == loanId) {
                return loan;
            }
        }
        return null;
    }
}
