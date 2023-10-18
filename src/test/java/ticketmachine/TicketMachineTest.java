package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	// S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
	void printTicketPriceNotEnought(){
		machine.insertMoney(20);

		assertEquals(false,machine.printTicket(),"le montant est insuffisant");
	}

	@Test
		// S4 :  on imprime le ticket si le montant inséré est suffisant
	void printTicketPriceEnought(){
		machine.insertMoney(50);

		assertEquals(true,machine.printTicket(),"le montant est suffisant");
	}

	@Test
		// S5 :  Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void printTicketBalance(){
		machine.insertMoney(80);
		machine.printTicket();

		assertEquals(80-PRICE,machine.getBalance(),"la balance n'est pas décrémentée");
	}

	@Test
		// S6 :  le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void MoneyCollectMAJ(){
		machine.insertMoney(80);
		assertEquals(0,machine.getTotal(),"le montant collecté est MAJ avant l'impression du ticket");
		machine.printTicket();
		assertEquals(PRICE,machine.getTotal(),"le montant collecté n'est pas MAJ");

	}

	@Test
		// S7 :  refund() rend correctement la monnaie
	void GiveBackChange(){
		machine.insertMoney(80);
		machine.printTicket();

		assertEquals(80-PRICE,machine.refund(),"la fonction refund ne rend pas la monnaie");

	}


	@Test
		// S8 :  refund() remet la balance à zéro
	void balance0(){
		machine.insertMoney(80);
		machine.refund();

		assertEquals(0,machine.getBalance(),"la fonction refund ne remet pas la balance à zéro");

	}


	@Test
		// S9 :  on ne peut pas insérer un montant négatif
	void InsertnegativeValues(){
		try{
			machine.insertMoney(-80);
			fail("doit lever une exception");
		}
		catch (IllegalArgumentException e){

		}
	}


	@Test
		// S10 :  on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	void createMachineTicketNegative(){
		try{
			TicketMachine machine = new TicketMachine(-15);
			fail("doit lever une exception");
		}
		catch (IllegalArgumentException e){

		}
	}


}
