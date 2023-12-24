import express, { json } from 'express'
import cors from 'cors'
import fetch from 'node-fetch'

const app = express()
const apikey = process.env.API_KEY
const apiUrl = 'http://localhost:8080'

app.disable('x-powered-by')
app.use(json())
app.use(
  cors({
    origin: 'http://localhost:5173',
    methods: 'GET,HEAD,PUT,PATCH,POST,DELETE',
    credentials: true,
    optionsSuccessStatus: 204
  })
)

const isEmpty = (object) => {
  return Object.keys(object) === 0
}

async function doFetch (url, method, headers, body) {
  const options = {
    method,
    headers
  }

  if (isEmpty(body)) { options.body = JSON.stringify(body) }

  const response = await fetch(url, options)

  if (response.status === 500) {
    return { status: 500, json: { message: 'Internal Server Error' } }
  }

  const json = await response.json()
  return { status: response.status, json }
}

app.use('/', async (req, res) => {
  const url = `${apiUrl}${req.url}`
  const headers = req.headers
  const method = req.method
  const body = req.body

  headers['Api-Key'] = apikey

  const response = await doFetch(url, method, headers, body)

  res.status(response.status).json(response.json)
})

app.listen(3000, () => {
  console.log('Server listening on port 3000')
})
