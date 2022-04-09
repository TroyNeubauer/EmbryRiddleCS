#Code obtained from: https://github.com/rdespoiu/PyAES
#
#ROBERTO DESPOIU
#CSC 333-601
from binascii import *

class AES:
    sBox = list()
    GF2 = list()
    GF3 = list()
    RC = list()


    def __init__(self):
        self.initTables()

    #Initialize Tables
    def initTables(self):
        #AES S-Box
        self.sBox = [
                    0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
                    0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0,
                    0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15,
                    0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
                    0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
                    0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF,
                    0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8,
                    0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
                    0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73,
                    0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
                    0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79,
                    0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
                    0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A,
                    0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E,
                    0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
                    0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
                    ]

        #GF 2^8 {2}
        self.GF2 =  [
                    0x00, 0x02, 0x04, 0x06, 0x08, 0x0a, 0x0c, 0x0e, 0x10, 0x12, 0x14, 0x16, 0x18, 0x1a, 0x1c, 0x1e, 
                    0x20, 0x22, 0x24, 0x26, 0x28, 0x2a, 0x2c, 0x2e, 0x30, 0x32, 0x34, 0x36, 0x38, 0x3a, 0x3c, 0x3e,
                    0x40, 0x42, 0x44, 0x46, 0x48, 0x4a, 0x4c, 0x4e, 0x50, 0x52, 0x54, 0x56, 0x58, 0x5a, 0x5c, 0x5e,
                    0x60, 0x62, 0x64, 0x66, 0x68, 0x6a, 0x6c, 0x6e, 0x70, 0x72, 0x74, 0x76, 0x78, 0x7a, 0x7c, 0x7e,
                    0x80, 0x82, 0x84, 0x86, 0x88, 0x8a, 0x8c, 0x8e, 0x90, 0x92, 0x94, 0x96, 0x98, 0x9a, 0x9c, 0x9e,
                    0xa0, 0xa2, 0xa4, 0xa6, 0xa8, 0xaa, 0xac, 0xae, 0xb0, 0xb2, 0xb4, 0xb6, 0xb8, 0xba, 0xbc, 0xbe,
                    0xc0, 0xc2, 0xc4, 0xc6, 0xc8, 0xca, 0xcc, 0xce, 0xd0, 0xd2, 0xd4, 0xd6, 0xd8, 0xda, 0xdc, 0xde,
                    0xe0, 0xe2, 0xe4, 0xe6, 0xe8, 0xea, 0xec, 0xee, 0xf0, 0xf2, 0xf4, 0xf6, 0xf8, 0xfa, 0xfc, 0xfe,
                    0x1b, 0x19, 0x1f, 0x1d, 0x13, 0x11, 0x17, 0x15, 0x0b, 0x09, 0x0f, 0x0d, 0x03, 0x01, 0x07, 0x05,
                    0x3b, 0x39, 0x3f, 0x3d, 0x33, 0x31, 0x37, 0x35, 0x2b, 0x29, 0x2f, 0x2d, 0x23, 0x21, 0x27, 0x25,
                    0x5b, 0x59, 0x5f, 0x5d, 0x53, 0x51, 0x57, 0x55, 0x4b, 0x49, 0x4f, 0x4d, 0x43, 0x41, 0x47, 0x45,
                    0x7b, 0x79, 0x7f, 0x7d, 0x73, 0x71, 0x77, 0x75, 0x6b, 0x69, 0x6f, 0x6d, 0x63, 0x61, 0x67, 0x65,
                    0x9b, 0x99, 0x9f, 0x9d, 0x93, 0x91, 0x97, 0x95, 0x8b, 0x89, 0x8f, 0x8d, 0x83, 0x81, 0x87, 0x85,
                    0xbb, 0xb9, 0xbf, 0xbd, 0xb3, 0xb1, 0xb7, 0xb5, 0xab, 0xa9, 0xaf, 0xad, 0xa3, 0xa1, 0xa7, 0xa5,
                    0xdb, 0xd9, 0xdf, 0xdd, 0xd3, 0xd1, 0xd7, 0xd5, 0xcb, 0xc9, 0xcf, 0xcd, 0xc3, 0xc1, 0xc7, 0xc5,
                    0xfb, 0xf9, 0xff, 0xfd, 0xf3, 0xf1, 0xf7, 0xf5, 0xeb, 0xe9, 0xef, 0xed, 0xe3, 0xe1, 0xe7, 0xe5
                    ]

        #GF 2^8 {3}
        self.GF3 =  [
                    0x00, 0x03, 0x06, 0x05, 0x0c, 0x0f, 0x0a, 0x09, 0x18, 0x1b, 0x1e, 0x1d, 0x14, 0x17, 0x12, 0x11, 
                    0x30, 0x33, 0x36, 0x35, 0x3c, 0x3f, 0x3a, 0x39, 0x28, 0x2b, 0x2e, 0x2d, 0x24, 0x27, 0x22, 0x21, 
                    0x60, 0x63, 0x66, 0x65, 0x6c, 0x6f, 0x6a, 0x69, 0x78, 0x7b, 0x7e, 0x7d, 0x74, 0x77, 0x72, 0x71, 
                    0x50, 0x53, 0x56, 0x55, 0x5c, 0x5f, 0x5a, 0x59, 0x48,0x4b, 0x4e, 0x4d, 0x44, 0x47, 0x42, 0x41, 
                    0xc0, 0xc3, 0xc6, 0xc5, 0xcc, 0xcf, 0xca, 0xc9, 0xd8, 0xdb, 0xde, 0xdd, 0xd4, 0xd7, 0xd2, 0xd1, 
                    0xf0, 0xf3, 0xf6, 0xf5, 0xfc, 0xff, 0xfa, 0xf9, 0xe8, 0xeb, 0xee, 0xed, 0xe4, 0xe7, 0xe2, 0xe1, 
                    0xa0, 0xa3, 0xa6, 0xa5, 0xac, 0xaf, 0xaa, 0xa9, 0xb8, 0xbb, 0xbe, 0xbd, 0xb4, 0xb7, 0xb2, 0xb1, 
                    0x90, 0x93, 0x96, 0x95, 0x9c, 0x9f, 0x9a, 0x99, 0x88, 0x8b, 0x8e, 0x8d, 0x84, 0x87, 0x82, 0x81, 
                    0x9b, 0x98, 0x9d, 0x9e, 0x97, 0x94, 0x91, 0x92, 0x83, 0x80, 0x85, 0x86, 0x8f, 0x8c, 0x89, 0x8a, 
                    0xab, 0xa8, 0xad, 0xae, 0xa7, 0xa4, 0xa1, 0xa2, 0xb3, 0xb0, 0xb5, 0xb6, 0xbf, 0xbc, 0xb9, 0xba, 
                    0xfb, 0xf8, 0xfd, 0xfe, 0xf7, 0xf4, 0xf1, 0xf2, 0xe3, 0xe0, 0xe5, 0xe6, 0xef, 0xec, 0xe9, 0xea, 
                    0xcb, 0xc8, 0xcd, 0xce, 0xc7, 0xc4, 0xc1, 0xc2, 0xd3, 0xd0, 0xd5, 0xd6, 0xdf, 0xdc, 0xd9, 0xda, 
                    0x5b, 0x58, 0x5d, 0x5e, 0x57, 0x54, 0x51, 0x52, 0x43, 0x40, 0x45, 0x46, 0x4f, 0x4c, 0x49, 0x4a, 
                    0x6b, 0x68, 0x6d, 0x6e, 0x67, 0x64, 0x61, 0x62, 0x73, 0x70, 0x75, 0x76, 0x7f, 0x7c, 0x79, 0x7a, 
                    0x3b, 0x38, 0x3d, 0x3e, 0x37, 0x34, 0x31, 0x32, 0x23, 0x20, 0x25, 0x26, 0x2f, 0x2c, 0x29, 0x2a, 
                    0x0b, 0x08, 0x0d, 0x0e, 0x07, 0x04, 0x01, 0x02, 0x13, 0x10, 0x15, 0x16, 0x1f, 0x1c, 0x19, 0x1a 
                    ]

        #RCON table for up to ten rounds
        self.RC = ["01", "02", "04", "08", "10", "20", "40", "80", "1b", "36"]

    #Initialize state matrix for a given plaintext
    def initStateMatrix(self, plaintext):
        state = []

        while plaintext:
            state.append(plaintext[:2])
            plaintext = plaintext[2:]

        return state

    #HELPER FUNCTIONS
    def xor(self, left, right):

        out = int('0x{}'.format(left), 0) ^ int('0x{}'.format(right), 0)
        out = str(hex(out))[2:]

        if len(out) < 2:
            out = "0" + out

        return out

    def rotateWord(self, word):
        return word[1:] + word[:1]

    def toHex(self, string):
        string = bytes(string, 'utf-8')
        string = str(hexlify(string))[2:-1]
        return string

    def toString(self, hexString):
        return str(unhexlify(hexString))[2:-1]

    def padding(self, hexString):
        return hexString + "".zfill(32 - len(hexString))


    
    #KEY SCHEDULE
    def gFunction(self, word, round):
        output = []
        outString = ''

        while word:
            output.append(word[0:2])
            word = word[2:]

        output = self.rotateWord(output)

        for index in range(len(output)):
            out = int("0x{}".format(output[index]), 0)
            out = str(hex(self.sBox[out]))[2:]

            if len(out) < 2:
                out = "0" + out

            output[index] = out

        output[0] = self.xor(output[0], self.RC[round])

        for char in output:
            outString += char

        return outString
    
    def keySchedule(self, key, round):
        words = []
        roundKey = [None, None, None, None]

        while key:
            words.append(key[:8])
            key = key[8:]


        roundKey[0] = self.xor(words[0], self.gFunction(words[3], round)).zfill(8)
        roundKey[1] = self.xor(words[1], roundKey[0]).zfill(8)
        roundKey[2] = self.xor(words[2], roundKey[1]).zfill(8)
        roundKey[3] = self.xor(words[3], roundKey[2]).zfill(8)

        return roundKey


    #KEY ADDITION
    def keyAddition(self, state, key):
        stateString = ''
        keyString = ''
        newState = []

        for byte in state:
            stateString += byte

        for word in key:
            keyString += word

        stateString = self.xor(stateString, keyString).zfill(len(stateString))
        
        while stateString:
            newState.append(stateString[:2])
            stateString = stateString[2:]
        
        return newState

    #BYTE SUBSTITUTION
    def byteSubstitution(self, state):
        newState = []

        for byte in state:
            out = int("0x{}".format(byte), 0)
            out = str(hex(self.sBox[out]))[2:]

            if len(out) < 2:
                out = "0" + out

            newState.append(out)

        return newState

    #SHIFT ROWS
    def shiftRows(self, state):
        newState = [None for k in range(16)]

        #No Shift
        newState[0], newState[4], newState[8], newState[12] = state[0], state[4], state[8], state[12]

        #First shift. Shift by one
        newState[1], newState[5], newState[9], newState[13] = state[5], state[9], state[13], state[1]

        #Second shift. Shift by two
        newState[2], newState[6], newState[10], newState[14] = state[10], state[14], state[2], state[6]

        #Third shift. Shift by three
        newState[3], newState[7], newState[11], newState[15] = state[15], state[3], state[7], state[11]

        return newState

    #MIX COLUMNS
    def mixColumn(self, subState):
        newSubState = []
        currentMultRound = 0

        while len(newSubState) < 4:
            out = int()

            if currentMultRound == 0:
                one = self.GF2[int("0x{}".format(subState[0]), 0)]
                two = self.GF3[int("0x{}".format(subState[1]), 0)]
                three = int("0x{}".format(subState[2]), 0)
                four = int("0x{}".format(subState[3]), 0)

                out = one ^ two ^ three ^ four

            elif currentMultRound == 1:
                one = int("0x{}".format(subState[0]), 0)
                two = self.GF2[int("0x{}".format(subState[1]), 0)]
                three = self.GF3[int("0x{}".format(subState[2]), 0)]
                four = int("0x{}".format(subState[3]), 0)

                out = one ^ two ^ three ^ four

            elif currentMultRound == 2:
                one = int("0x{}".format(subState[0]), 0)
                two = int("0x{}".format(subState[1]), 0)
                three = self.GF2[int("0x{}".format(subState[2]), 0)]
                four = self.GF3[int("0x{}".format(subState[3]), 0)]

                out = one ^ two ^ three ^ four

            else:
                one = self.GF3[int("0x{}".format(subState[0]), 0)]
                two = int("0x{}".format(subState[1]), 0)
                three = int("0x{}".format(subState[2]), 0)
                four = self.GF2[int("0x{}".format(subState[3]), 0)]

                out = one ^ two ^ three ^ four

            out = str(hex(out))[2:]
            if len(out) < 2:
                out = "0" + out

            newSubState.append(out)
            currentMultRound += 1

        return newSubState

    def mixColumns(self, state):
        subState = []
        while state:
            subState += self.mixColumn(state[:4])
            state = state[4:]

        return subState

    def encryptOneRound(self, plaintext, key):
        #For encrypting one round

        #Initialize state matrix from given plaintext
        state = self.initStateMatrix(plaintext)

        #Perform key addition/round key
        state = self.keyAddition(state, key)

        #Perform byte substitution
        state = self.byteSubstitution(state)

        #Perform shift rows
        state = self.shiftRows(state)
        
        #Perform mix columns
        state = self.mixColumns(state)
        
        #Generate key schedule for given key
        key = self.keySchedule(key, 0)
        
        #Final round key addition for plaintext/key
        state = self.keyAddition(state, key)        
        
        return state

    def encryptFIPS197(self):
        #Check array contains expected values after each round with the given plaintext/key
        #input from the FIPS-197 document. For each round, an assertion is made that check[round] == state.
        #If there are no assertion errors, the test is passed.

        check = [
                ["19","3d","e3","be","a0","f4","e2","2b","9a","c6","8d","2a","e9","f8","48","08"],
                ["a4","9c","7f","f2","68","9f","35","2b","6b","5b","ea","43","02","6a","50","49"],
                ["aa","8f","5f","03","61","dd","e3","ef","82","d2","4a","d2","68","32","46","9a"],
                ["48","6c","4e","ee","67","1d","9d","0d","4d","e3","b1","38","d6","5f","58","e7"],
                ["e0","92","7f","e8","c8","63","63","c0","d9","b1","35","50","85","b8","be","01"],
                ["f1","00","6f","55","c1","92","4c","ef","7c","c8","8b","32","5d","b5","d5","0c"],
                ["26","0e","2e","17","3d","41","b7","7d","e8","64","72","a9","fd","d2","8b","25"],
                ["5a","41","42","b1","19","49","dc","1f","a3","e0","19","65","7a","8c","04","0c"],
                ["ea","83","5c","f0","04","45","33","2d","65","5d","98","ad","85","96","b0","c5"],
                ["eb","40","f2","1e","59","2e","38","84","8b","a1","13","e7","1b","c3","42","d2"]
                ]
        desiredOutput = ["39", "25", "84", "1d", "02", "dc", "09", "fb", "dc", "11", "85", "97", "19", "6a", "0b", "32"]
                        

        #Input plaintext/key as per FIPS-197 test
        state = ['32', '43', 'f6', 'a8', '88', '5a', '30', '8d', '31', '31', '98', 'a2', 'e0', '37', '07', '34']
        key = "2b7e151628aed2a6abf7158809cf4f3c"

        INITIALSTATE = ""
        INITIALKEY = ""
        keyCopy = key

        for byte in state:
            INITIALSTATE += byte + " "
        while keyCopy:
            INITIALKEY += keyCopy[:2] + " "
            keyCopy = keyCopy[2:]



        #Initial Key Addition Layer
        state = self.keyAddition(state, key)

        for round in range(10):

            #Checks against what should be returned each round. For testing purposes
            try:
                assert state == check[round]
            except AssertionError:
                print("\n****************")
                print("ASSERTION ERROR")
                print("****************\n")

                print("DESIRED STATE:")
                print(check[round])

                print("\nYOUR STATE:")
                print(state)

                print("\ROUND: {}".format(round+1))
                return
                


            state = self.byteSubstitution(state)

            state = self.shiftRows(state)

            if round < 9:
                state = self.mixColumns(state)

            newKey = ""
            for byte in key:
                newKey += byte

            key = newKey

            key = self.keySchedule(key, round)

            state = self.keyAddition(state, key)

        try:
            assert state == desiredOutput
            print("\n****************")
            print("You passed!")
            print("****************\n")
        except AssertionError:
            print("Oh no, it failed!")
            print("DESIRED OUTPUT:")
            print(desiredOutput)

        prettyCipherText = ""

        for byte in state:
            prettyCipherText += byte + " "

        print("Input Plaintext:")
        print(INITIALSTATE)

        print("\nInput Key:")
        print(INITIALKEY)

        print("\nCiphertext Output:")
        print(prettyCipherText)

        return state

    def encrypt(self, plaintext, key):
        #ENCRYPTS TEN ROUNDS

        state = self.initStateMatrix(plaintext)

        #Initial Key Addition Layer
        state = self.keyAddition(state, key)

        for round in range(10):

            state = self.byteSubstitution(state)

            state = self.shiftRows(state)

            if round < 9:
                state = self.mixColumns(state)

            newKey = ""
            for byte in key:
                newKey += byte

            print(key)
            key = newKey

            key = self.keySchedule(key, round)

            state = self.keyAddition(state, key)

        return state

def main():
    while True:

        AESTest = AES()

        print("Please indicate which method you would like to test:")
        print("1 - Encrypt one round of AES (128 bit hex input)")
        print("2 - Encrypt 10 rounds of AES (128 bit hex input)")
        print("3 - Test input from FIPS-197 (128 bit hex input)")
        print("4 - Encrypt 10 rounds of AES (128 bit plaintext/string input)")
        print("5 - Compute output of mix colums step (128 bit hex input)")
        print("NOTE: OPTION 4 IS INCOMPLETE AND DOES NOT WORK CORRECTLY YET.")
        print("Press enter to quit")

        inp = input(">  ")

        if (inp == "1"):
            print("Please enter a 128 bit hexadecimal plaintext:")
            plaintext = input(">  ")

            print("Please enter a 128 bit hexadecimal key:")
            key = input(">  ")

            oneRound = AESTest.encryptOneRound(plaintext, key)
            
            prettyCipherText = ""

            for byte in oneRound:
                prettyCipherText += byte + " "

            print("\n\nPlaintext:")
            print(plaintext)

            print("\nCiphertext:")
            print(prettyCipherText)
            print("\n")

        elif inp == "2":
            print("Please enter a 128 bit hexadecimal plaintext:")
            plaintext = input(">  ")

            print("Please enter a 128 bit hexadecimal key:")
            key = input(">  ")

            tenRound = AESTest.encrypt(plaintext, key)

            prettyCipherText = ""

            for byte in tenRound:
                prettyCipherText += byte + " "

            print("\n\nPlaintext:")
            print(plaintext)

            print("\nCiphertext:")
            print(prettyCipherText)
            print("\n")         

        elif inp == "3":
            FIPS197Test = AESTest.encryptFIPS197()
            print(FIPS197Test)

        elif inp == "4":
            print("Please enter a plaintext (string) message to encrypt:")
            plaintext = input(">  ")

            print("Please enter a 128 bit hexadecimal key: (plaintext will be implemented soon)")
            key = input(">  ")

            originalPlaintext = plaintext
            plaintext = AESTest.toHex(plaintext)
            plaintextBlocks = []

            if len(plaintext) < 32:
                plaintext = AESTest.padding(plaintext)

            else:       
                while plaintext:
                    plaintextBlocks.append(plaintext[:32])
                    plaintext = plaintext[32:]

                if len(plaintextBlocks[-1]) < 32:
                    plaintextBlocks[-1] = AESTest.padding(plaintextBlocks[-1])

            if plaintext:
                encryptedMessage = AESTest.encrypt(plaintext, key)
                cipherText = ""

                for byte in encryptedMessage:
                    cipherText += byte

                #Decode cipherText (i.e. hex --> string)
                cipherText = AESTest.toString(cipherText)

                print("Plaintext:")
                print(originalPlaintext)

                print("\nCiphertext:")
                print(cipherText)




            else:
                encryptedBlocks = []

                for block in plaintextBlocks:
                    encryptedBlocks.append(AESTest.encrypt(block, key))

        if (inp == "5"):
            print("Please enter a 128 bit hexadecimal state:")
            state = input(">  ")

            state = AESTest.mixColumns(state)
            
            prettyState = ""

            for byte in state:
                prettyState += byte + " "

            print("\nState:")
            print(prettyState)
            print("\n")


        else:
            break

if __name__ == "__main__":
    main()

 
