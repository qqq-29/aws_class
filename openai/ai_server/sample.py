from google.genai import types

chat_history = []
chat_history.append(types.Content(
	role = "user",
	parts = [types.Part.from_text(text="오늘 날씨어때?")]
	))
chat_history.append(types.Content(
	role = "model",
	parts = [types.Part.from_text(text="오늘은 맑고 화창")]
	))
chat_history.append(types.Content(
	role = "user",
	parts = [types.Part.from_text(text="그럼 오늘 뭐할까")]
	))
chat_history.append(types.Content(
	role = "model",
	parts = [types.Part.from_text(text="산책하는건 어떄요?")]
	))


db={
	"history" : [
		types.Content(role = "user",parts = [types.Part.from_text(text="오늘 날씨어때?")]),
		types.Content(role = "model",parts = [types.Part.from_text(text="오늘은 맑고 화창")]),
		types.Content(role = "user",parts = [types.Part.from_text(text="그럼 오늘 뭐할까")]),
		types.Content(role = "model",parts = [types.Part.from_text(text="산책하는건 어떄요?")]),
	],
	"summary" : ""
}