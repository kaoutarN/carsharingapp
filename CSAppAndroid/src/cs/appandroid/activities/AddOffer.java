package cs.appandroid.activities;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddOffer extends Activity implements OnClickListener
{
	// Components define into step 1
	private EditText startingAddressEditText;
	private EditText finishingAddressEditText;
	
	private EditText dateRouteEditText;
	private Button dateRouteButton;
	
	private EditText timeRouteEditText;
	private Button timeRouteButton;
	
	private Button incrementNumberOfPassengersButton;
	private TextView numberOfPassengersTextView;
	private Button decrementNumberOfPassengersButton;
	
	private Button goToNextStepButton;
	
	
	// Components define into step 2
	private TextView startingAddressSummaryTextView;
	private TextView finishingAddressSummaryTextView;
	private TextView dateRouteSummaryTextView;
	private TextView numberOfPassengersSummaryTextView;
	private Button decrementPricePerPassengerButton;
	private TextView pricePerPassengerTextview;
	private Button incrementPricePerPassengerButton;
	private Button proposeOfferButton;
	
	
	private int routeYear;
	private int routeMonth;
	private int routeDay;
	
	private int routeHour;
	private int routeMinute;
	
	private int numberOfPassengers;
	
	private int pricePerPassenger;
	
	private static final int DATE_ROUTE_DIALOG_ID = 0;
	private static final int TIME_ROUTE_DIALOG_ID = 1;
	 
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    
	    // Default number of passengers
	    numberOfPassengers = 3;
	    
	    //Default price per passenger
	    pricePerPassenger = 8;

	    displayAddOfferScreen();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
			
		Bundle params = getIntent().getExtras();
		
		if(params != null)
		{
			 if(params.containsKey("addOfferNextStep"))
				 displayAddOfferNextStepScreen();
		}
		else displayAddOfferScreen();
	}

	@Override
	public void onClick(View v)
	{
		if(v == dateRouteButton)
		{
			// Get the current date
	        Calendar dateCalendar = Calendar.getInstance();
	        routeYear  			  = dateCalendar.get(Calendar.YEAR);
	        routeMonth 			  = dateCalendar.get(Calendar.MONTH);
	        routeDay   			  = dateCalendar.get(Calendar.DAY_OF_MONTH);
	        
	        showDialog(DATE_ROUTE_DIALOG_ID);
		}
		else if(v == timeRouteButton)
		{
			Calendar timeCalendar  = Calendar.getInstance();
			routeHour   		   = timeCalendar.get(Calendar.HOUR_OF_DAY);
			routeMinute 		   = timeCalendar.get(Calendar.MINUTE);
			
			showDialog(TIME_ROUTE_DIALOG_ID);
		}
		else if(v == goToNextStepButton)
		{
			String test = startingAddressEditText.getText().toString();
			Log.v("test", test);
			
			// To display the addofferstep2 screen
			getIntent().putExtra("addOfferNextStep", true);
			getIntent().putExtra("startingAddress", startingAddressEditText.getText().toString());
			getIntent().putExtra("finishingAddress", finishingAddressEditText.getText().toString());
			getIntent().putExtra("dateRoute", dateRouteEditText.getText().toString());
			getIntent().putExtra("timeRoute", timeRouteEditText.getText().toString());
			getIntent().putExtra("numberOfPassengers", numberOfPassengers);
			
			onResume();
		}
		else if(v == incrementNumberOfPassengersButton)
		{
			if(numberOfPassengers != 6)
			{
				numberOfPassengers++;
			
				numberOfPassengersTextView.setText(numberOfPassengers + " pl.");
			}
		}
		else if(v == decrementNumberOfPassengersButton)
		{
			if(numberOfPassengers != 1)
			{
				numberOfPassengers--;
			
				numberOfPassengersTextView.setText(numberOfPassengers + " pl.");
			}
		}
		else if(v == decrementPricePerPassengerButton)
		{
			if(pricePerPassenger != 1)
			{
				pricePerPassenger--;
			    
				pricePerPassengerTextview.setText(pricePerPassenger + "�");
			}
		}
		else if(v == incrementPricePerPassengerButton)
		{	
			if(pricePerPassenger != 15)
			{
				pricePerPassenger++;
			
				pricePerPassengerTextview.setText(pricePerPassenger + "�");
			}
		}
		else if(v == proposeOfferButton)
		{
			
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int dialogId)
	{
	    switch(dialogId)
	    {
	     	case DATE_ROUTE_DIALOG_ID: return new DatePickerDialog(this,
	                    									 	   routeDateSetListener,
	                    									 	   routeYear, routeMonth, routeDay);
	     	
	     	case TIME_ROUTE_DIALOG_ID: return new TimePickerDialog(this,
	     														   routeTimeSetListener,
	     														   routeHour, routeMinute, false);

	    }
	    return null;
	}
	
	public void displayAddOfferScreen()
	{
		setContentView(R.layout.addoffer);
		
		startingAddressEditText  = (EditText)findViewById(R.id.starting_address_edittext);
		finishingAddressEditText = (EditText)findViewById(R.id.finishing_address_edittext);
		
	    dateRouteEditText = (EditText)findViewById(R.id.date_route_edittext);
	    dateRouteButton   = (Button)findViewById(R.id.date_route_button);
	    dateRouteButton.setOnClickListener(this);
	    
	    timeRouteEditText = (EditText)findViewById(R.id.hour_route_edittext);
	    timeRouteButton   = (Button)findViewById(R.id.hour_route_button);
	    timeRouteButton.setOnClickListener(this);
	    
	    // Go to the next step (step 2)
	    goToNextStepButton = (Button)findViewById(R.id.go_to_next_step_button);
	    goToNextStepButton.setOnClickListener(this);
	    
	    incrementNumberOfPassengersButton = (Button)findViewById(R.id.increment_number_of_passenger_button);
	    incrementNumberOfPassengersButton.setOnClickListener(this);
	    
	    numberOfPassengersTextView        = (TextView)findViewById(R.id.number_of_passengers_textview);
	    
	    decrementNumberOfPassengersButton = (Button)findViewById(R.id.decrement_number_of_passenger_button);
	    decrementNumberOfPassengersButton.setOnClickListener(this);
	}
	
	public void displayAddOfferNextStepScreen()
	{
		setContentView(R.layout.addofferstep2);
		
		startingAddressSummaryTextView    = (TextView)findViewById(R.id.starting_address_summary_textview);
		finishingAddressSummaryTextView   = (TextView)findViewById(R.id.finishing_address_summary_textview);
		dateRouteSummaryTextView          = (TextView)findViewById(R.id.date_route_summary_textview);
		numberOfPassengersSummaryTextView = (TextView)findViewById(R.id.number_of_passengers_summary_textview);
		
		Bundle addOfferFirstScreen = getIntent().getExtras();
		
		String startingAddress  = addOfferFirstScreen.getString("startingAddress");
		String finishingAddress = addOfferFirstScreen.getString("finishingAddress");
		String dateRoute        = addOfferFirstScreen.getString("dateRoute");
		
		startingAddressSummaryTextView.setText("D�part: " + startingAddress);
		finishingAddressSummaryTextView.setText("Arriv�e: " + finishingAddress);
		dateRouteSummaryTextView.setText("Date: " + dateRoute);
		numberOfPassengersSummaryTextView.setText(numberOfPassengers + " places restantes");
		
		decrementPricePerPassengerButton = (Button)findViewById(R.id.decrement_price_per_passenger_button);
		decrementPricePerPassengerButton.setOnClickListener(this);
		
		pricePerPassengerTextview        = (TextView)findViewById(R.id.price_per_passenger_textview);
		pricePerPassengerTextview.setText(pricePerPassenger + "�");
		
		incrementPricePerPassengerButton = (Button)findViewById(R.id.increment_price_per_passenger_button);	
		incrementPricePerPassengerButton.setOnClickListener(this);
		
		proposeOfferButton = (Button)findViewById(R.id.propose_offer_button);
		proposeOfferButton.setOnClickListener(this);
	}
	
	// The callback received when the user sets the route date in the dialog
    private DatePickerDialog.OnDateSetListener routeDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
    	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    	{
    		routeYear = year;
    		routeMonth = monthOfYear;
    		routeDay = dayOfMonth;
    		
    		updateDateRouteDisplay();
    	}
    };
    
    // The callback received when the user sets the route time in the dialog
    private TimePickerDialog.OnTimeSetListener routeTimeSetListener = new TimePickerDialog.OnTimeSetListener()
    {
    	public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    	{
    		routeHour   = hourOfDay;
    		routeMinute = minute;
    		
    		updateTimeRouteDisplay();
    	}
    };

    // Update the route date display in the TextView
    private void updateDateRouteDisplay()
    {
    	dateRouteEditText.setText(new StringBuilder()
		                     	  // Month is 0 based so add 1
		                     	  .append(routeYear + 1).append("-")
		                     	  .append(routeMonth).append("-")
		                     	  .append(routeDay));
    }
    
    // Update the route time display in the TextView
    private void updateTimeRouteDisplay()
    {
	   	timeRouteEditText.setText(new StringBuilder()
						     	  .append(routeHour).append(":")
						     	  .append(routeMinute));
    }
}
