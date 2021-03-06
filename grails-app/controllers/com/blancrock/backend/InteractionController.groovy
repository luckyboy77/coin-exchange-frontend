/***************************************************************************** 
* Copyright 2016 Aurora Solutions 
* 
*    http://www.aurorasolutions.io 
* 
* Aurora Solutions is an innovative services and product company at 
* the forefront of the software industry, with processes and practices 
* involving Domain Driven Design(DDD), Agile methodologies to build 
* scalable, secure, reliable and high performance products.
* 
* Coin Exchange is a high performance exchange system specialized for
* Crypto currency trading. It has different general purpose uses such as
* independent deposit and withdrawal channels for Bitcoin and Litecoin,
* but can also act as a standalone exchange that can be used with
* different asset classes.
* Coin Exchange uses state of the art technologies such as ASP.NET REST API,
* AngularJS and NUnit. It also uses design patterns for complex event
* processing and handling of thousands of transactions per second, such as
* Domain Driven Designing, Disruptor Pattern and CQRS With Event Sourcing.
* 
* Licensed under the Apache License, Version 2.0 (the "License"); 
* you may not use this file except in compliance with the License. 
* You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
* Unless required by applicable law or agreed to in writing, software 
* distributed under the License is distributed on an "AS IS" BASIS, 
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
* See the License for the specific language governing permissions and 
* limitations under the License. 
*****************************************************************************/


package com.blancrock.backend

import javax.persistence.Convert

/**
 * Created by Vladimir Havenchyk.
 */
class InteractionController {
    def queryService

    def getOhlcInfo() {
        Map queryResult = queryService.getOhlcInfo('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def getTickerInfo() {
        Map queryResult = queryService.getTickerInfo('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def getCurrencyPairs() {
        Map queryResult = queryService.getCurrencyPairs()

        response.status = queryResult.status
        render queryResult.value
    }

    def getRates() {
        Map queryResult = queryService.getRates()

        response.status = queryResult.status
        render queryResult.value
    }

    def queryOpenOrders() {
        Map queryResult = queryService.queryOpenOrders(true, '')

        response.status = queryResult.status
        render queryResult.value
    }

    def queryClosedOrders() {
        Map queryResult = queryService.queryClosedOrders(true, '', '', '', '', '')

        response.status = queryResult.status
        render queryResult.value
    }

    def getTradeHistory() {
        Map queryResult = queryService.getTradeHistory('', '')

        response.status = queryResult.status
        render queryResult.value
    }

    // Gets the details when a trade is clicked
    def getTradeDetails(){
        def orderParams = request.JSON
        String tradeId = orderParams['tradeId']

        Map queryResult = queryService.getTradeDetails(tradeId)

        response.status = queryResult.status
        render queryResult.value
    }

    def createNewOrder() {
        def orderParams = request.JSON
        def order = orderParams['order']

        String currencyPair = order['pair']
        String type = order['orderType']
        String side = order['type']
        BigDecimal volume = new BigDecimal(order['volume'] as String)
        BigDecimal price = 0
        if (type == 'Limit'){
            price = new BigDecimal(order['price'] as String)
        }

        Map queryResult = queryService.createOrder(currencyPair, type, side, volume, price)

        response.status = queryResult.status
        render queryResult.value
    }

    def newOrderSimpleBids() {
        Map queryResult = queryService.newOrderSimpleBids('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def newOrderSimpleAsks() {
        Map queryResult = queryService.newOrderSimpleAsks('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def cancelOrder() {
        def orderParams = request.JSON
        String orderId = orderParams['OrderId']
        
        Map queryResult = queryService.cancelOrder(orderId)

        response.status = queryResult.status
        render queryResult.value
    }

    def showOrderDetails() {
        def orderParams = request.JSON
        //@todo: need to use only style for all calls
        String orderId = orderParams['orderId']
        
        Map queryResult = queryService.showOrderDetails(orderId)

        response.status = queryResult.status
        render queryResult.value
    }

    // Gets teh trades when an order is clicked for order details
    def showTradeDetails() {
        def orderParams = request.JSON
        String orderId = orderParams['orderId']
        
        Map queryResult = queryService.showTradeDetails(orderId)

        response.status = queryResult.status
        render queryResult.value
    }

    def getRecentTrades() {
        Map queryResult = queryService.getRecentTrades('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def getBids() {
        Map queryResult = queryService.getBids('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def getAsks() {
        Map queryResult = queryService.getAsks('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def getDepth() {
        Map queryResult = queryService.getDepth('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def getOrderBookCummulativeVolume() {
        Map queryResult = queryService.getOrderBookCummulativeVolume('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def getSpread() {
        Map queryResult = queryService.getSpread('BTCLTC')

        response.status = queryResult.status
        render queryResult.value
    }

    def createNewDepositAddress(){
        def depositParams = request.JSON
        String currency = depositParams['currency']
        Map newAddressQueryResult = queryService.createNewDepositAddress(currency)

        response.status = newAddressQueryResult.status
        if (newAddressQueryResult.status == 500 && newAddressQueryResult.value == ""){
            render "Too many addresses";
        }
        else{
            Map addressListQueryResult =  queryService.getDepositAddresses(currency)
            response.status = addressListQueryResult.status

            render addressListQueryResult.value
        }
    }

    def getDepositLimits(){
        def depositParams = request.JSON
        String currency = depositParams['currency']
        Map queryResult = queryService.getDepositLimits(currency)

        response.status = queryResult.status
        render queryResult.value
    }

    def getDepositAddresses(){
        def depositParams = request.JSON
        String currency = depositParams['currency']
        Map queryResult = queryService.getDepositAddresses(currency)

        response.status = queryResult.status
        render queryResult.value
    }

    def getRecentDeposits(){
        def depositParams = request.JSON
        String currency = depositParams['currency']
        Map queryResult = queryService.getRecentDeposits(currency)

        response.status = queryResult.status
        render queryResult.value
    }

    def getRecentWithdrawals(){
        def withdrawParams = request.JSON
        String currency = withdrawParams['currency']
        Map queryResult = queryService.getRecentWithdrawals(currency)

        response.status = queryResult.status
        render queryResult.value
    }

    def saveWithdrawAddress(){
        def withdrawParams = request.JSON
        String currency = withdrawParams['currency']
        String bitcoinAddress = withdrawParams['bitcoinAddress']
        String description = withdrawParams['description']
        // ToDo: Provide and get the paarameters and pass them on
        Map queryResult = queryService.saveWithdrawAddress(currency, bitcoinAddress, description)

        response.status = queryResult.status
        render queryResult.value
    }

    def getWithdrawLimits(){
        def withdrawParams = request.JSON
        String currency = withdrawParams['currency']
        Map queryResult = queryService.getWithdrawLimits(currency)

        response.status = queryResult.status
        render queryResult.value
    }

    def getWithdrawAddresses(){
        def withdrawParams = request.JSON
        String currency = withdrawParams['currency']
        Map queryResult = queryService.getWithdrawAddresses(currency)

        response.status = queryResult.status
        render queryResult.value
    }

    def commitWithdraw(){
        def withdrawParams = request.JSON
        String currency = withdrawParams['currency']
        String bitcoinAddress = withdrawParams['bitcoinAddress']

        String amountString = withdrawParams['amount']
        BigDecimal amount = amountString.toBigDecimal();
        Map queryResult = queryService.commitWithdraw(currency, bitcoinAddress, amount)

        response.status = queryResult.status
        render queryResult.value
    }

    def deleteWithdrawAddress(){
        def withdrawParams = request.JSON
        String bitcoinAddress = withdrawParams['bitcoinAddress']
        Map queryResult = queryService.deleteWithdrawAddress(bitcoinAddress)

        response.status = queryResult.status
        render queryResult.value
    }

    def cancelWithdraw(){
        def withdrawParams = request.JSON
        String bitcoinAddress = withdrawParams['withdrawId']
        Map queryResult = queryService.cancelWithdraw(bitcoinAddress)

        response.status = queryResult.status
        render queryResult.value
    }

    def getLedgers(){
        def ledgerParams = request.JSON
        String currency = ledgerParams['currency']
        Map queryResult = queryService.getLedgersForCurrency(currency)

        response.status = queryResult.status
        render queryResult.value
    }

    def getAllLedgers(){
        Map queryResult = queryService.getAllLedgers()

        response.status = queryResult.status
        render queryResult.value
    }

    def getLedgerDetails(){
        def ledgerParams = request.JSON
        String ledgerId = ledgerParams['ledgerId']
        Map queryResult = queryService.getLedgerDetails(ledgerId)

        response.status = queryResult.status
        render queryResult.value
    }

    def getDepositTierLimits(){
        Map queryResult = queryService.getDepositTierLimits()

        response.status = queryResult.status
        render queryResult.value
    }

    def getWithdrawTierLimits(){
        Map queryResult = queryService.getWithdrawTierLimits()

        response.status = queryResult.status
        render queryResult.value
    }

    def getTierStatus(){
        Map queryResult = queryService.getTierStatus()

        response.status = queryResult.status
        render queryResult.value
    }

    def getTier1Details(){
        Map queryResult = queryService.getTier1Details()

        response.status = queryResult.status
        render queryResult.value
    }

    def getTier2Details(){
        Map queryResult = queryService.getTier2Details()

        response.status = queryResult.status
        render queryResult.value
    }

    def getTier3Details(){
        Map queryResult = queryService.getTier3Details()

        response.status = queryResult.status
        render queryResult.value
    }

    def applyForTier1(){
        def tier1Params = request.JSON
        String fullName = tier1Params['fullName']
        String dateOfBirth = tier1Params['dateOfBirth']
        String country = tier1Params['country']
        String phoneNumber = tier1Params['phoneNumber']
        Map queryResult = queryService.applyForTier1(fullName, dateOfBirth, country, phoneNumber)

        response.status = queryResult.status
        render queryResult.value
    }

    def applyForTier2(){
        def tier2Params = request.JSON
        String addressLine1 = tier2Params['addressLine1']
        String addressLine2 = tier2Params['addressLine2']
        String addressLine3 = tier2Params['addressLine3']
        String state = tier2Params['state']
        String city = tier2Params['city']
        String zip = tier2Params['zip']
        Map queryResult = queryService.applyForTier2(addressLine1, addressLine2, addressLine3, state, city, zip)

        response.status = queryResult.status
        render queryResult.value
    }

    def applyForTier3(){
        def tier3Params = request.JSON
        String nationalId = tier3Params['nationalId']
        String documentType = tier3Params['documentType']
        String fileName = tier3Params['fileName']
        String ssn = tier3Params['ssn']
        Map queryResult = queryService.applyForTier3(nationalId, documentType, fileName, ssn)

        response.status = queryResult.status
        render queryResult.value
    }

    def getAccountSettings(){
        Map queryResult = queryService.getAccountSettings()

        response.status = queryResult.status
        render queryResult.value
    }

    def changeSettings(){
        def settingsParams = request.JSON
        String email = settingsParams['email']
        String pgpPublicKey = settingsParams['pgpPublicKey']
        String language = settingsParams['language']
        String timeZone = settingsParams['timeZone']
        String isDefaultAutoLogout = settingsParams['isDefaultAutoLogout']
        String autoLogoutMinutes = settingsParams['autoLogoutMinutes']
        Map queryResult = queryService.changeSettings(email, pgpPublicKey, language, timeZone, isDefaultAutoLogout, autoLogoutMinutes)

        response.status = queryResult.status
        render queryResult.value
    }

    def getSecurityKeys(){
        Map queryResult = queryService.getSecurityKeys()

        response.status = queryResult.status
        render queryResult.value
    }

    def createNewKey(){
        def params = request.JSON
        String keyDescription = params['keyDescription']
        boolean enableExpirationDate = params['enableExpirationDate']
        String expirationDate = params['expirationDate']
        String expirationTime = params['expirationTime']

        boolean enableStartDate = params['enableStartDate']
        String queryStartDate = params['queryStartDate']
        String queryStartTime = params['queryStartTime']

        boolean enableEndDate = params['enableEndDate']
        String queryEndDate = params['queryEndDate']
        String queryEndTime = params['queryEndTime']
        List permissions = params['permissions']

        def expirationDateTime = '';
        def queryStartDateTime = '';
        def queryEndDateTime = '';

        if (enableExpirationDate){
            expirationDateTime = parseDateTime(expirationDate, expirationTime)
        }
        else if (enableExpirationDate == 'undefined' || enableExpirationDate == 'null') {
            enableExpirationDate = false;
        }
        if (enableStartDate){
            queryStartDateTime = parseDateTime(queryStartDate, queryStartTime)
            enableStartDate = true;
        }
        else if (enableStartDate == 'undefined' || enableStartDate == 'null') {
            enableStartDate = false;
        }
        if (enableEndDate){
            queryEndDateTime = parseDateTime(queryEndDate, queryEndTime)
        }
        else if (enableEndDate == 'undefined' || enableEndDate == 'null') {
            enableEndDate = false;
        }
        Map queryResult = queryService.createNewKey(keyDescription, enableExpirationDate, expirationDateTime.toString(),
                enableStartDate, queryStartDateTime.toString(), enableEndDate, queryEndDateTime.toString(), permissions)

        response.status = queryResult.status
        render queryResult.value
    }

    def sendNotifications(){
        def settingsParams = request.JSON
        boolean adminEmails = settingsParams['adminEmails']
        boolean newsLetterEmails = settingsParams['newsLetterEmails']
        Map queryResult = queryService.sendNotifications(adminEmails, newsLetterEmails)

        response.status = queryResult.status
        render queryResult.value
    }

    def changePassword(){
        def settingsParams = request.JSON
        String oldPassword = settingsParams['oldPassword']
        String newPassword = settingsParams['newPassword']
        Map queryResult = queryService.changePassword(oldPassword, newPassword)

        response.status = queryResult.status
        render queryResult.value
    }

    def parseDateTime(String date, String time){
        def dateSplit = date.split('T')
        def timeSplit = time.split('T')
        def dateTemp = dateSplit[0]
        def timeTemp = timeSplit[1].take(8)
        return new StringBuilder().append(dateTemp)
                .append(' ')
                .append(timeTemp)
    }
}
