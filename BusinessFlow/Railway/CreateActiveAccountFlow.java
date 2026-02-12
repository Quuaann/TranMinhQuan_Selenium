package Railway;

import Constant.TabMenu;
import DataObject.Account;
import Guerrillamail.MailFake;

public class CreateActiveAccountFlow extends RegisterPage{
	
	public void createActiveAccount(Account newAccount) {
	    this.gotoPage(TabMenu.REGISTER, RegisterPage.class);
	    this.enterRegistrationInfo(newAccount);
	    MailFake mailFake = new MailFake();
	    mailFake.openMailFake();
	    mailFake.activeNewMail(newAccount.getUsername());
	}
}