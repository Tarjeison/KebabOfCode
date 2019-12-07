IDENTIFICATION DIVISION.
PROGRAM-ID. FINDPW.
    
DATA DIVISION.
    WORKING-STORAGE SECTION.
    
    01 WS-PASSWORD.
        05 PASSWORD                     PIC 9(6) VALUE 158126.
        05 PASSWORD-CHAR                PIC X(6).
        05 END-VALUE                    PIC 9(6) VALUE 624574.
        05 DIFF                         PIC 9(6).
        
    01 WS-COMPARE.
        05 COUNTER                      PIC 9.
        05 COUNTER-PLUS-ONE             PIC 9.
        05 CUR-PW-INCREASING            PIC 9.
            88 PW-INCREASING            VALUE 1.
            88 PW-NOT-INCREASING        VALUE 0.
        05 CUR-PW-DOUBLE-DIGITS         PIC 9.
            88 PW-DOUBLE-DIGITS         VALUE 1.
            88 PW-NOT-DOUBLE-DIGITS     VALUE 0.
            
        
    01  NUM-VALID-CODES                 PIC 9(6).
            
        
    
PROCEDURE DIVISION.
    0000-MAIN.
        SUBTRACT END-VALUE FROM PASSWORD GIVING DIFF
        MOVE 0 TO NUM-VALID-CODES
        PERFORM DIFF TIMES
            PERFORM 0100-CHECK-IF-VALID-PW
            ADD 1 TO PASSWORD
        END-PERFORM
        DISPLAY NUM-VALID-CODES
        STOP RUN
        .
    0000-END.
    
    0100-CHECK-IF-VALID-PW.
        MOVE 0 TO COUNTER
        SET PW-INCREASING TO TRUE 
        SET PW-NOT-DOUBLE-DIGITS TO TRUE 
        
        PERFORM VARYING COUNTER FROM 1 BY 1 UNTIL COUNTER=6 OR PW-NOT-INCREASING
            ADD 1 TO COUNTER GIVING COUNTER-PLUS-ONE
            
            IF PASSWORD(COUNTER-PLUS-ONE:1) < PASSWORD(COUNTER:1)
                SET PW-NOT-INCREASING TO TRUE
            END-IF
            
            IF PASSWORD(COUNTER-PLUS-ONE:1) = PASSWORD(COUNTER:1)
                SET PW-DOUBLE-DIGITS TO TRUE
            END-IF
            
        END-PERFORM
        IF PW-INCREASING AND PW-DOUBLE-DIGITS
            ADD 1 TO NUM-VALID-CODES
        END-IF
        .
    0100-END.
    
END PROGRAM FINDPW.
