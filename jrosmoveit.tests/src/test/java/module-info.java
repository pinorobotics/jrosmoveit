open module jrosmoveit.tests {
    requires jrosclient;
    requires jrosmoveit;
    requires jrosrviztools;
    requires id.xfunction;
    
    requires org.junit.jupiter.api;
    requires jrostf2;
    
    exports pinorobotics.jrosmoveit.tests;
}