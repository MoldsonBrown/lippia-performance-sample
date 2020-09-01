@Performance @Example
Feature: Existing jmx file
COMO un usuario del sitio
QUIERO ejecutar peticiones a las Sucursales
PARA verificar que el mismo responda apropiadamente.

  Scenario Outline: Run performance with existing jmx file
	Given configure the performance test with '<iterations>' iterations '<users>' users and a ramp time of '<ramp_time>' seconds
	When run the script '<script_name>'
	And remplace variables with '<json>'
	
  @1ps	
  Examples:
  | iterations	|users	|ramp_time	|script_name						                            |json								                                          |
  | 1		  	    |1		  |1		    |lippia_example               |exampleJson	                        |

  @2ps
  Examples:
  | iterations	|users	|ramp_time	|script_name						                       |json								                                          |
  | 1		    	  |600		|300			    |lippia_example               |exampleJson	                        |

  @3ps
  Examples:
  | iterations	|users	|ramp_time	|script_name						                       |json								                                          |
  | 1		    	  |900		|300			    |lippia_example               |exampleJson	                        |

  @4ps
  Examples:
  | iterations	|users	|ramp_time	|script_name						                       |json								                                          |
  | 1		    	  |1200		|300			    |lippia_example               |exampleJson	                        |
  
  @5ps
  Examples:
  | iterations	|users	|ramp_time	|script_name						                       |json								                                          |
  | 1		    	  |1500		|300			    |lippia_example               |exampleJson	                        |
  
  @6ps
  Examples:
  | iterations	|users	|ramp_time	|script_name						                       |json								                                          |
  | 1		    	  |1800		|300			    |lippia_example               |exampleJson	                        |
  
  @7ps
  Examples:
  | iterations	|users	|ramp_time	|script_name						                       |json								                                          |
  | 1		    	  |2100		|300			    |lippia_example               |exampleJson	                        |
  
  @8ps
  Examples:
  | iterations	|users	|ramp_time	|script_name						                       |json								                                          |
  | 1		    	  |2400		|300			    |lippia_example               |exampleJson	                        |
  
  @9ps
  Examples:
  | iterations	|users	|ramp_time	|script_name						                       |json								                                          |
  | 1		    	  |2700		|300			    |lippia_example               |exampleJson	                        |
  
  @10ps
  Examples:
  | iterations	|users	|ramp_time	|script_name						                       |json								                                          |
  | 1		    	  |3000		|300			    |lippia_example               |exampleJson	                        |
  
