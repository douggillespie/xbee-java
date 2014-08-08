  Introduction
  ------------
  This is a sample Java application to show how to read XBee analog inputs 
  of the device attached to the serial/USB port of your PC.
  
  The application configures an IO line of the XBee device as ADC. Then 
  periodically reads its value and prints it in the output console.


  Files
  ----------
    * com.digi.xbee.api.handlelocaladc.MainApp.java:
      Main application class. Instantiates an XBee device, establishes a 
      serial connection with it, configures the IO line and reads its analog 
      value.


  Requirements
  ------------
  To run this example you will need:
  
    * One XBee radio in API mode and its corresponding carrier board 
      (XBIB or XBee Development Board).


  Example setup
  -------------
    1) Plug the XBee radio into the XBee adapter and connect it to your
       computer's USB or serial port.
       
    2) Ensure that the module is in API mode.
       For further information on how to perform this task, go to [...]
       
    3) Set the port and baud rate of the XBee radio in the MainApp class.
       If you do not know the serial/USB port where your module is connected to,
       see [...]
       
    4) The final step is to connect a voltage variable source to the pin 
       configured as ADC (light sensor, temperature sensor, etc). For testing 
       purposes we recommend to use a potentiometer. Depending on the carrier 
       board you are using you will need to follow a different set of 
       instructions to connect it:
         - XBIB-U-DEV board:
             * Isolate the pin configured as ADC so it does not use the 
               functionality provided by the board.
             * Connect the potentiometer to VCC, to the pin configured as ADC 
               and to GND. Something similar to this:
               
                   O   VCC
                   |
                   <
                   >___ XBee device pin (ADC)
                   >
                   <
                  _|_
                   -   GND
               
             * If you prefer not to isolate the pin of the board and not to use 
               a potentiometer, you can still test the example. The IO line 
               configured as ADC (DIO1/AD1) is connected to the SW3 user button 
               of the XBIB-U-DEV board, so the analog value will change from 
               nothing to all depending on the status of the button.
         
         - XBee Development Board:
             * Connect a voltage to VRef pin of the device (you can take it 
               from the Vcc pin).
             * Configure the micro-switch of AD1 line to "Potentiometer", this 
               way the DIO1/AD1 line of the device will be connected to the 
               board's potentiometer

         NOTE: It is recommended to verify the capabilities of the pins used 
               in the example as well as the electrical characteristics in the 
               product manual of your XBee Device to ensure that everything is 
               configured correctly.


  Running the example
  -------------------
  First, build and launch the application.
  To test its functionality follow these steps:
  
    1) Rotate the potentiometer.
       
    2) Verify that the value displayed in the output console is changing.
       