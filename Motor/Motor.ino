int led1 = 5;

int IN3 = 3;
int IN4 = 2;

const int pulsador1 = 4;
int estado;

int input;


int pinzumbador = 7;    // pin del zumbador
int frecuencia = 500;      // frecuencia correspondiente a la nota La

int pinSensor = 6;

int valLDR = 0;



void setup() {
  Serial.begin(9600);
  pinMode (IN4, OUTPUT);
  pinMode (IN3, OUTPUT);
  pinMode(pulsador1, INPUT);
  pinMode(led1, OUTPUT);
}
void loop() {
//  derecha();
//  digitalWrite(IN4, LOW);
//  digitalWrite(IN3, LOW);

  if (Serial.available() > 0) {
    input = Serial.read();
    if (input == '1') {
      digitalWrite(led1, HIGH);
      digitalWrite(IN4, HIGH);
      delay(1200);
      digitalWrite(IN4, LOW);
      delay(2500);
      digitalWrite(IN3, HIGH);
      delay(750);
      digitalWrite(IN3, LOW);
      tone(pinzumbador, frecuencia);   // inicia el zumbido
      delay(500);
      noTone(pinzumbador);
//    } else {
//     digitalWrite(led1, HIGH);
//      digitalWrite(IN4, HIGH);
//      delay(500);
//      digitalWrite(IN4, LOW);
//      delay(1000);
//      digitalWrite(IN3, HIGH);
//      delay(500);
//      digitalWrite(IN3, LOW);
//    }
  }
}
}

//void derecha() {
//  estado = digitalRead(pulsador1);
//  if (estado == HIGH) {
//    digitalWrite(led1, HIGH);
//    digitalWrite(IN4, HIGH);
//    digitalWrite(IN3, LOW);
//    tone(pinzumbador, frecuencia);   // inicia el zumbido
//    delay(500);
//    noTone(pinzumbador);
//    //Senso();
//    delay(25000);
//  } else {
//    digitalWrite(IN4, LOW);
//    digitalWrite(IN3, HIGH);
//    digitalWrite(led1, LOW);
//  }
//}
//
//void Senso() {
//  valLDR = analogRead(pinSensor);
//  if (valLDR != HIGH) {
//    digitalWrite(IN4, LOW);
//    digitalWrite(IN3, LOW);
//  }
//}
