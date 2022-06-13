#define USE_ARDUINO_INTERRUPTS true
#include <PulseSensorPlayground.h>

//const int OUTPUT_TYPE = SERIAL_PLOTTER;

const int PULSE_SENSOR_COUNT = 4;
const int PULSE_INPUT0 = A0;    
const int PULSE_FADE0 = 5;
const int PULSE_INPUT1 = A1;
const int PULSE_FADE1 = 11;
const int PULSE_INPUT2 = A2;
const int PULSE_FADE2 = 4;
const int PULSE_INPUT3 = A3;
const int PULSE_FADE3 = 8;
const int THRESHOLD = 550;  

PulseSensorPlayground pulseSensor(4);

void setup() {
  Serial.begin(9600);

  pulseSensor.analogInput(PULSE_INPUT0, 0);
  pulseSensor.fadeOnPulse(PULSE_FADE0, 0);
  pulseSensor.analogInput(PULSE_INPUT1, 1);
  pulseSensor.fadeOnPulse(PULSE_FADE1, 1);
  pulseSensor.analogInput(PULSE_INPUT2, 2);
  pulseSensor.fadeOnPulse(PULSE_FADE2, 2);
  pulseSensor.analogInput(PULSE_INPUT3, 3);
  pulseSensor.fadeOnPulse(PULSE_FADE3, 3);
  pulseSensor.setSerial(Serial);
  //pulseSensor.setOutputType(OUTPUT_TYPE);
  pulseSensor.setThreshold(THRESHOLD);
  
  if (pulseSensor.begin()) {
    Serial.println("lobulUrechii manaStanga manaDreapta picior");  //Apare cand se porneste sau se reseteaza
  }
}

void loop() {
  int myBPM = pulseSensor.getBeatsPerMinute(); 
  pulseSensor.outputSample();
  for (int i = 0; i < PULSE_SENSOR_COUNT; ++i) {
    if (pulseSensor.sawStartOfBeat(i)) {  
      pulseSensor.outputBeat(i);
 }
    
  }
  
  delay(250);

}
