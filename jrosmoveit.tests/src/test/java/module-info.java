open module jrosmoveit.tests {
    requires jrosclient;
    requires jrosmoveit;
    requires id.xfunction;
    
    requires org.junit.jupiter.api;
    
    exports pinorobotics.jrosmoveit.tests;
}