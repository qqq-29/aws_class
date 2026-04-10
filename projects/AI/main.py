from fastapi import FastAPI, Form

app = FastAPI()

@app.get("/")
async def index():
    return {"message": "Hello FastAPI"}

@app.post("/test")
async def index(msg: str = Form(...)):
    print(f"메시지: {msg}") 

    return {"msg": "fastAPI"}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("main:app", port=8000, reload=True)