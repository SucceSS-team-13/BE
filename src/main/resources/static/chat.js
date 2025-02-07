// SockJS와 STOMP 라이브러리 필요 - npm install @stomp/stompjs sockjs-client
import Stomp from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const socket = new SockJS('/ws-chat');
const stompClient = Stomp.over(socket);
let roomId = 0;

stompClient.onConnect = onConnect;
stompClient.onWebSocketError = function (error) {
    console.error('WebSocket 에러 : ' + error);
}
stompClient.onStompError = function (frame) {npmnpm
    console.error('오류 보고 : ' + frame.headers['message']);
    console.error('세부 정보 : ' + frame.body);
}

// STOMP 연결 성공
function onConnect() {
        console.log('WebSocket 연결 성공 : ' + frame);
        setConnectedStatus(); // 코드 작성 필요
        stompClient.subscribe(`/topic/user-request`, () => {});
        stompClient.subscribe('/topic/ai-response', () => {});
}

// 연결되었을 때 UI 변경
function setConnectedStatus() {
}

// 연결 및 해제
function connect() {
    stompClient.connect();
}
function disconnect() {
    stompClient.disconnect();
}

// 이벤트 발생
$(function() {
    // 채팅방 생성 OR 기존 채팅방 누를 경우 connect() 호출
    // 채팅방 나갈 경우 disconnect() 호출
    // 메시지 보낼 경우 sendMessage() 호출
});