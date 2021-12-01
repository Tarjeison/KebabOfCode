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
        05 J                            PIC 9.
        05 COUNTER-MATCHING-DIGITS      PIC 9.
        05 NEXT-DIGIT                   PIC 99.
        05 PREVIOUS-MATCHING-DIGIT      PIC 99.
        05 J-PLUS-ONE                   PIC 9.
        05 SEARCH-MATCHING-DIGITS       PIC 9.
            88 SEARCH-MATCHING-FINISHED VALUE 1.
            88 SEARCH-NOT-FINISHED      VALUE 0.
        05 CUR-PW-DOUBLE                PIC 9.
            88 PW-DOUBLE                VALUE 1.
            88 PW-NOT-DOUBLE            VALUE 0.
        05 CUR-PW-INVALID               PIC 9.
            88 PW-VALID                 VALUE 1.
            88 PW-INVALID               VALUE 0.
            
        
    01  NUM-VALID-CODES                 PIC 9(6).
            
    01  DEBUG                           PIC 9 VALUE 0.
        88 DEBUG-ON                     VALUE 1.
        88 DEBUG-OFF                    VALUE 0.
    
PROCEDURE DIVISION.
    0000-INIT.
        SET DEBUG-OFF TO TRUE
        IF DEBUG-ON 
            PERFORM 0010-DEBUG
        ELSE 
            PERFORM 0050-MAIN
        END-IF
        .
    0000-END.
    
    0010-DEBUG.
        MOVE 335556 TO PASSWORD
        DISPLAY PASSWORD
        PERFORM 0100-CHECK-IF-VALID-PW
        STOP RUN.
    0010-END.
    
    0050-MAIN.
        SUBTRACT END-VALUE FROM PASSWORD GIVING DIFF
        MOVE 0 TO NUM-VALID-CODES
        ADD 1 TO DIFF
        PERFORM DIFF TIMES
            PERFORM 0100-CHECK-IF-VALID-PW
            ADD 1 TO PASSWORD
        END-PERFORM
        DISPLAY NUM-VALID-CODES
        STOP RUN
        .
    0050-END.
    
    0100-CHECK-IF-VALID-PW.
        MOVE 0 TO COUNTER
        SET PW-VALID TO TRUE 
        SET PW-NOT-DOUBLE TO TRUE 
        MOVE 99 TO PREVIOUS-MATCHING-DIGIT
        PERFORM VARYING COUNTER FROM 1 BY 1 UNTIL COUNTER=6 OR PW-INVALID
            ADD 1 TO COUNTER GIVING COUNTER-PLUS-ONE
            IF DEBUG-ON
                DISPLAY PASSWORD(COUNTER:1) ':' PASSWORD(COUNTER-PLUS-ONE:1)
            END-IF
            
            IF PASSWORD(COUNTER-PLUS-ONE:1) < PASSWORD(COUNTER:1)
                SET PW-INVALID TO TRUE
                IF DEBUG-ON
                    DISPLAY 'Not increasing numbers'
                END-IF
            END-IF
            
#           Using PIC 99 field to have an invalid digit (99). When comparing the other data type needs to be equal.
            MOVE PASSWORD(COUNTER-PLUS-ONE:1) TO NEXT-DIGIT
            IF NEXT-DIGIT NOT = PREVIOUS-MATCHING-DIGIT
                MOVE 99 TO PREVIOUS-MATCHING-DIGIT
            END-IF
            
            IF (PASSWORD(COUNTER-PLUS-ONE:1) = PASSWORD(COUNTER:1)) AND (NEXT-DIGIT NOT = PREVIOUS-MATCHING-DIGIT)

                SET SEARCH-NOT-FINISHED TO TRUE
                MOVE 2 TO COUNTER-MATCHING-DIGITS
                MOVE PASSWORD(COUNTER:1) TO PREVIOUS-MATCHING-DIGIT
                
                IF DEBUG-ON
                        DISPLAY 'Searching for double'
                END-IF
                    
                PERFORM VARYING J FROM COUNTER-PLUS-ONE UNTIL J=6 OR SEARCH-MATCHING-FINISHED
                    ADD 1 TO J GIVING J-PLUS-ONE
                    
                    IF DEBUG-ON
                        DISPLAY PASSWORD(J-PLUS-ONE:1) ';' PASSWORD(COUNTER:1)
                    END-IF
                    
                    IF PASSWORD(J-PLUS-ONE:1) = PASSWORD(COUNTER:1)
                        ADD 1 TO COUNTER-MATCHING-DIGITS
                    ELSE
                        SET SEARCH-MATCHING-FINISHED TO TRUE 
                    END-IF
                END-PERFORM
                
                IF DEBUG-ON
                    DISPLAY 'Number of matching digits: ' COUNTER-MATCHING-DIGITS
                END-IF
                IF COUNTER-MATCHING-DIGITS = 2 
                    SET PW-DOUBLE TO TRUE 
                END-IF
            END-IF
            
        END-PERFORM
        IF PW-VALID AND PW-DOUBLE
            IF DEBUG-ON
                DISPLAY 'Valid'
            END-IF
            ADD 1 TO NUM-VALID-CODES
        END-IF
        .
    0100-END.

    
END PROGRAM FINDPW.

