open module jrosmoveit.tests {
    requires jrosclient;
    requires jrosmoveit;
    requires jrosrviztools;
    requires id.xfunction;
    
    requires org.junit.jupiter.api;
    
    exports pinorobotics.jrosmoveit.tests;
}