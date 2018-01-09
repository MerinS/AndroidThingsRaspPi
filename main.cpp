/* mbed EM-406 GPS Module Library
 * Copyright (c) 2008-2010, sford
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
 
#include "mbed.h"
#include <string.h>
#include "GPS.h"

// Peripherals
GPS gps(p9,p10);
Serial gsm(p13,p14);
Serial pc(USBTX,USBRX);

int main() {

    gsm.baud(9600);
    pc.baud(9600);

    char buf[40];
    char buf1[40];
    char buf2= 0x1A;

    gsm.printf("AT\r\n");
    gsm.scanf("%s",buf);
    pc.printf("%s\n",buf);
    gsm.scanf("%s",buf1);
    pc.printf("%s\n",buf1);

    gsm.printf("AT+CSMP=17,167,0,0\r\n");
    gsm.scanf("%s",buf);
    gsm.scanf("%s",buf1);
    pc.printf("%s\n",buf);
    pc.printf("%s\n",buf1);

    gsm.printf("AT+CMGF=1\r\n");
    gsm.scanf("%s",buf);
    gsm.scanf("%s",buf1);
    pc.printf("%s\n",buf);
    pc.printf("%s\n",buf1);

    while (1) {
        if (gps.sample()) {
            //if (gps.latitude!=0.0 && gps.longitude!=0.0) {
            pc.printf("message sent");
            gsm.printf("AT+CMGS=\"7889296549\"\r\n");
            gsm.scanf("%s",buf);
            gsm.scanf("%s",buf1);
            pc.printf("%s\n",buf);
            pc.printf("%s\n",buf1);

            gsm.printf("Latitude = %f"",""\nLongitude = %f %c\r\n",gps.latitude,gps.longitude,buf2);
            gsm.scanf("%s",buf);
            gsm.scanf("%s",buf1);
            pc.printf("%s\n",buf);
            pc.printf("%s\n",buf1);
 

            break;
        }
        pc.printf("LATITUDE = %f   LONGITUDE = %f",gps.latitude,gps.longitude);
        wait(1);
    }
    pc.printf("end of operation");

    return 0;
}