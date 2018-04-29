package main

import (
	"fmt"
	"log"
	"net"
	"sync"
	"time"
)

func portScan(targetToScan string, portStart int, portEnd int, openPorts *[]int, targetPorts map[int]string, log func(int, string)) {
	//wait groups
	wg := new(sync.WaitGroup)
	//loop in ports (start to end)
	for port := portStart; port <= portEnd; port++ {
		wg.Add(1) //add 1 wait
		go grabBanner(wg, openPorts, targetPorts, targetToScan, port, log)
	}
	// Wait for all threads to finish
	wg.Wait()
}

func grabBanner(wg *sync.WaitGroup, openPorts *[]int, targetPorts map[int]string, ip string, port int, log func(int, string)) {
	//done on exit
	defer wg.Done()
	//try connect
	address := fmt.Sprintf("%s:%d", ip, port)
	connection, err := net.DialTimeout("tcp", address, 10*time.Second)
	if err != nil {
		return
	}
	defer connection.Close()
	// append open port to slice
	*openPorts = append(*openPorts, port)
	//log
	log(port, "")
	// See if server offers anything to read
	buffer := make([]byte, 4096)
	connection.SetReadDeadline(time.Now().Add(time.Second * 5))
	// Set timeout
	numBytesRead, err := connection.Read(buffer)
	if err != nil {
		return
	}
	buff := string(buffer[0:numBytesRead])
	// here we add to map port and banner
	targetPorts[port] = buff
	//log
	log(port, buff)
}

func logf(port int, buff string) {
	if buff != "" {
		log.Printf("+ Banner of port %d\n%s\n", port, buff)
	} else if port != -1 {
		log.Printf("+ Port %d: Open\n", port)
	}
}

func main() {
	//
	targetPorts := make(map[int]string)
	openPorts := make([]int, 0)
	//
	portScan("westfall.com.br", 20, 8080, &openPorts, targetPorts, logf)
}
